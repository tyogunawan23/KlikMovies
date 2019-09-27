package id.code.klikmovies.function.home.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.code.klikmovies.function.home.fragment.FavoriteFragment
import id.code.klikmovies.function.home.fragment.PopularFragment
import id.code.klikmovies.function.home.fragment.TopRatesFragment
import id.code.klikmovies.util.PrefManager

class TabAdapter : FragmentPagerAdapter {

    var pages :List<Fragment>
    lateinit var context: Context

    constructor(fm: FragmentManager, behavior: Int, context: Context) : super(fm, behavior) {
        pages = listOf( PopularFragment(), TopRatesFragment(), FavoriteFragment())
        this.context = context
    }

    override fun getItem(position: Int): Fragment {
        return pages[position];
    }

    override fun getCount(): Int {
       return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Popular"
            1 -> "Top Rates"
            else -> "Favorite" + "("+ getTotalFavoriteSize() +")"
        }
    }

    fun getTotalFavoriteSize () : Int? {
        return PrefManager(context).getFavoriteMovie()?.size
    }

}