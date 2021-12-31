package com.kotlin.customview.test1

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.customview.R

/**
 * 图片+文字
 */
class ImgWithTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val img by lazy { ImageView(context) }
    private val text by lazy { TextView(context) }

    init {
        img.setBackgroundResource(R.drawable.mv1)
        text.setText("美女")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var resultWidth = 200
        var resultHeight = 300
        resultWidth = resolveSize(resultWidth,widthMeasureSpec)
        resultHeight = resolveSize(resultHeight,heightMeasureSpec)
        setMeasuredDimension(resultWidth,resultHeight)
    }

}