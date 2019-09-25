package id.code.klikmovies.function.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import id.code.klikmovies.R
import id.code.klikmovies.databinding.ActivityMainBinding
import id.code.klikmovies.function.home.adapter.TabAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.toolbar.title = getString(R.string.app_name)
        binding.viewPager.adapter = TabAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.tabsLayout.setupWithViewPager(binding.viewPager)


    }
}
