package com.example.stufffmanager.ui.features

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FeaturesFragment) : FragmentStateAdapter(activity) {

    // 返回页面数量
    override fun getItemCount(): Int = 3

    // 根据位置创建对应的 Fragment
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CheckinFragment()
            1 -> DepartmentFragment()
            2 -> AwradFragment()
            else -> Fragment()
        }
    }
}
