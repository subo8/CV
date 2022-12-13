package com.miu.cv

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.cv.fragments.ContactFragment
import com.miu.cv.fragments.ExperienceFragment
import com.miu.cv.fragments.HomeFragment
import com.miu.cv.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = intent.getSerializableExtra("USER") as User

        val adapter = MyAdapter(supportFragmentManager)
        adapter.createFragment(HomeFragment().newInstance(user), "Home")
        adapter.createFragment(ExperienceFragment().newInstance(user), "Experience")
        adapter.createFragment(ContactFragment().newInstance(user), "Contact")

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.work)
        tabs.getTabAt(2)!!.setIcon(R.drawable.contact)
//        tabs.tabGravity = TabLayout.GRAVITY_FILL
//        TabLayoutMediator(tabs, viewpager) {tab, position ->
//            when(position) {
//                0 -> {
//                    tab.setIcon(R.drawable.home)
//                }
//                1 -> {
//                    tab.setIcon(R.drawable.work)
//                }
//                2 -> {
//                    tab.setIcon(R.drawable.contact)
//                }
//            }
//        }.attach()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                val pref = getSharedPreferences("cv", Context.MODE_PRIVATE)
                val edit = pref.edit()
                edit.remove("auth")
                edit.remove("email")
                edit.apply()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}