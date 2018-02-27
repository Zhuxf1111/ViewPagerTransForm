package com.example.hasee.recyclerviewitemdecoration

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }


    private fun initView() {
        val viewpager = findViewById<CustomeViewPager>(R.id.view_pager)
        viewpager.offscreenPageLimit = 5
        viewpager.pageMargin = -50
        viewpager.setPageTransformer(true) { page, position ->
            val v = Math.abs(position)
            val v1 = (0.2 * (v * v)).toFloat()
            page?.scaleY = (1 - v1)
        }
        viewpager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val tv = TextView(container.context)
                if (position % 2 == 0) {
                    tv.setBackgroundColor(Color.RED)
                } else {
                    tv.setBackgroundColor(Color.BLUE)
                }
                tv.text = "position = $position"
                val layoutParams = ViewPager.LayoutParams()
                layoutParams.width = 200
                tv.gravity = Gravity.CENTER
                container.addView(tv, layoutParams)
                return tv
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View?)
            }


            override fun getCount(): Int {
                return 5
            }

        }

    }
}
