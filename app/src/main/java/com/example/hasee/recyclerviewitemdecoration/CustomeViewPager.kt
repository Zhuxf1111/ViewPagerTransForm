package com.example.hasee.recyclerviewitemdecoration

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log

/**
 * Created by HASEE on 2017/12/26.
 */
class CustomeViewPager : ViewPager {


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    private fun initView() {
//        this.addOnPageChangeListener(this)
        this.isChildrenDrawingOrderEnabled = true
        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (position == currentItem) {//方向-->
                    if (positionOffset in 0.5f..1.0f) {
                        currentTopPosition = currentItem + 1
                    } else {
                        currentTopPosition = currentItem
                    }
                } else {//方向 --<
                    if (positionOffset in 0.0f..0.5f) {
                        currentTopPosition = currentItem - 1
                    } else {
                        currentTopPosition = currentItem
                    }
                }
            }

            override fun onPageSelected(position: Int) {
//                currentTopPosition = position
//                postInvalidate()
            }

        })
    }

    private var currentTopPosition = 0


    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
//        return super.getChildDrawingOrder(childCount, i)
        if (i == 0) {
            Log.i("zxf", "==========================================================")
        }
        var result = 0
        if (currentTopPosition < 0) {
            result = i
        } else {
            if (i < currentTopPosition) {
                result = i
            } else {
                result = childCount - 1 - (i - currentTopPosition)
            }
        }
        Log.i("zxf", "getChildDrawingOrder currentTopPosition : $currentTopPosition currentItem : $currentItem draw int : $i  result : $result")
        if (i == childCount - 1) {
            Log.i("zxf", "==========================================================")
        }
        return result
    }

}