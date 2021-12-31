package com.kotlin.customview.test1

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.kotlin.customview.etx.e

/**
 * 自定义正方形View
 */
class RectImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //1.触发原有的自我测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //2.1获取原有测量结果
        var resultWidth = measuredWidth
        var resultHeight = measuredHeight
        "start resultWidth $resultWidth resultHeight $resultHeight ".e()
        //2.3重新计算宽高
        if (resultWidth > resultHeight){
            resultWidth = resultHeight
        }else{
            resultHeight = resultWidth
        }
        "end resultWidth $resultWidth resultHeight $resultHeight ".e()
        //3.保存新的结果
        setMeasuredDimension(resultWidth,resultHeight)
    }

}