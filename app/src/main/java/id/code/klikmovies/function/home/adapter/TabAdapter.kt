package id.code.klikmovies.function.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.code.klikmovies.function.home.fragment.FavoriteFragment
import id.code.klikmovies.function.home.fragment.PopularFragment
import id.code.klikmovies.function.home.fragment.TopRatesFragment

class TabAdapter : FragmentPagerAdapter {

    var pages :List<Fragment>;

    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior) {
        pages = listOf(TopRatesFragment(), PopularFragment(), FavoriteFragment())
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
            else -> "Favorite"
        }
    }

}