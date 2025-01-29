package com.nareshtech.tabnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO 3: set up the adapter for the ViewPager2
        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)
        viewPager2.adapter = ViewPagerAdapter(this)

        // TODO 5: set up the TabLayout
        val tabLayout:TabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout,viewPager2){ tab, position ->
            when(position){
                0->tab.text = "Red"
                1->tab.text = "Blue"
                2->tab.text = "Green"
            }
        }.attach()
    }

    // TODO 2: set up an adapter for the ViewPager2
    class ViewPagerAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            when(position){
                0->return RedFragment()
                1->return BlueFragment()
                2->return GreenFragment()
                else->return RedFragment()
            }
        }

    }
}