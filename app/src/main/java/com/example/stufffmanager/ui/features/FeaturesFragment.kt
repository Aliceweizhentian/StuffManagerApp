package com.example.stufffmanager.ui.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.stufffmanager.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FeaturesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_features, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = requireView().findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = requireView().findViewById<ViewPager2>(R.id.view_pager)

        // 创建 ViewPager 适配器
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // 将 TabLayout 和 ViewPager2 绑定
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "考勤管理"
                1 -> tab.text = "部门管理"
                2 -> tab.text = "奖惩管理"
            }
        }.attach()
    }
}