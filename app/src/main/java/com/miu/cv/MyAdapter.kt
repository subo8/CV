package com.miu.cv

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.miu.cv.fragments.ContactFragment
import com.miu.cv.fragments.ExperienceFragment
import com.miu.cv.fragments.HomeFragment

class MyAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitleList[position]

    fun createFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

}

//class MyAdapter(fm: FragmentManager, lc: Lifecycle): FragmentStateAdapter(fm, lc) {
//    private val fragmentList = ArrayList<Fragment>()
//    private val fragmentTitleList = ArrayList<String>()
//
//    override fun getItemCount() = fragmentList.size
//
//    fun createFrag(fragment: Fragment, title: String) {
//        fragmentList.add(fragment)
//        fragmentTitleList.add(title)
//    }
//    override fun createFragment(position: Int): Fragment {
//        return when(position) {
//            0 -> HomeFragment()
//            1 -> ExperienceFragment()
//            2 -> ContactFragment()
//            else -> Fragment()
//        }
//    }
//}