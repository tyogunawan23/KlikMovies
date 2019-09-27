package id.code.klikmovies.function.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import id.code.klikmovies.App
import id.code.klikmovies.R
import id.code.klikmovies.databinding.ActivityMainBinding
import id.code.klikmovies.function.base.BaseActivity
import id.code.klikmovies.function.home.adapter.TabAdapter
import id.code.klikmovies.model.GenreParser
import id.code.klikmovies.util.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity :BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var broadCastReceiver : BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.toolbar.title = getString(R.string.app_name)
        binding.viewPager.adapter = TabAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this@MainActivity)
        binding.tabsLayout.setupWithViewPager(binding.viewPager)

        App().services.getAllGenre().enqueue(object  : Callback<GenreParser> {
            override fun onFailure(call: Call<GenreParser>, t: Throwable) {
            }
            override fun onResponse(call: Call<GenreParser>, response: Response<GenreParser>) {
                PrefManager(this@MainActivity).setGenreList(genre = response.body()?.results)
            }
        })

        broadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(contxt: Context?, intent: Intent?) {
                when (intent?.action) {
                    App.Companion.BROADCAST_FAVORITE_CHANGED -> notifyTabAdapter()
                }
            }
        }

    }


    override fun onStart() {
        super.onStart()
        regisBroadcast()
    }

    override fun onStop() {
        super.onStop()
        unregisBroadcast()
    }


    fun regisBroadcast (){
        try {
            registerReceiver(broadCastReceiver, IntentFilter(App.Companion.BROADCAST_FAVORITE_CHANGED))
        }catch (e :Exception){
            e.printStackTrace()
        }
    }

    fun unregisBroadcast (){
        try {
           unregisterReceiver(broadCastReceiver)
        }catch (e :Exception){
            e.printStackTrace()
        }

    }

    fun notifyTabAdapter (){
        (binding.viewPager.adapter as TabAdapter).notifyDataSetChanged()
    }
}
