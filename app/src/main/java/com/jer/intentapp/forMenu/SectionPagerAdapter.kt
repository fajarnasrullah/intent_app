package com.jer.intentapp.forMenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var appName: String = ""

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {


        val fragment = TabFragment1()
        fragment.arguments = Bundle().apply {
            putInt(TabFragment1.GET_NUMBER, position + 1)
            putString(TabFragment1.GET_STRING, appName)
        }

        return fragment

//        var fragment: Fragment? = null
//        when(position) {
//            0 -> fragment = TabFragment1()
//            1 -> fragment = TabFragment2()
//        }
//        return fragment as Fragment
    }
}