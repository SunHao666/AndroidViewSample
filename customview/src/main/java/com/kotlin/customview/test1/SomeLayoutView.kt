package com.kotlin.customview.test1

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

class SomeLayoutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeheight = MeasureSpec.getMode(heightMeasureSpec)
        val sizeheight = MeasureSpec.getSize(heightMeasureSpec)

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val lp = childView.layoutParams
            var childWidthSpec: Int
            when (lp.width) {
                MATCH_PARENT ->{
                    //沾满父View剩余空间，剩余空间根据widthMeasureSpec
                    if (modeWidth == MeasureSpec.EXACTLY || modeWidth == MeasureSpec.AT_MOST){
                        childWidthSpec = MeasureSpec.makeMeasureSpec(sizeWidth, MeasureSpec.EXACTLY)
                    }else{
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                WRAP_CONTENT ->{
                    //沾满父View剩余空间，剩余空间根据widthMeasureSpec
                    if (modeWidth == MeasureSpec.EXACTLY || modeWidth == MeasureSpec.AT_MOST){
                        childWidthSpec = MeasureSpec.makeMeasureSpec(sizeWidth, MeasureSpec.AT_MOST)
                    }else{
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                else -> {
                    //精确值 如48dp
                    childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY)
                }
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}