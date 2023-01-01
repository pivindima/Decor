package com.pivin.decor.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.pivin.decor.R
import com.pivin.decor.databinding.ActivityMainBinding
import com.pivin.decor.presentation.fragment.CategoriesFragment
import com.pivin.decor.presentation.fragment.LiveFragment
import com.pivin.decor.presentation.fragment.StaticFragment
import com.pivin.decor.presentation.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragments = arrayListOf(
            CategoriesFragment(),
            LiveFragment(),
            StaticFragment(),
//            FlashFragment()
        )
        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.menu_categories
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.menu_live
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.menu_static
//                    3 -> binding.bottomNavigationView.selectedItemId = R.id.menu_flash
                }

            }
        })
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_categories -> binding.viewPager.currentItem = 0
                R.id.menu_live -> binding.viewPager.currentItem = 1
                R.id.menu_static -> binding.viewPager.currentItem = 2
//                R.id.menu_flash -> binding.viewPager.currentItem = 3
            }
            true
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}