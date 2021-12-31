package com.kotlin.customview.compose.number

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.NonNull
import com.kotlin.customview.R
import com.kotlin.customview.databinding.ViewNumberInputBinding

/**
 * 数字递增、减View
 */
class NumberInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var mBinding: ViewNumberInputBinding? = null
    private var mListener: ((Int) -> Unit)? = null
    private var mCurrentNumber = 0
    var min = -30
    var max = 30
    var step = 1
    var textSize = 15
    var defaultValue = 0
        get() = field
        set(value) {
            field = value
            mCurrentNumber = value
            updateNumber()
        }
    var disable = false
    var btnBackground = -1

    var number: Int
        set(value) {
            mCurrentNumber = value
            updateNumber()
        }
        get() {
            return mCurrentNumber
        }


    init {
        //获取自定义属性
        initAttrs(context, attrs)
        //加载组合布局
        initView(context)
        //点击事件
        setUpEvent()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val attrs =
            context.obtainStyledAttributes(attrs, R.styleable.NumberInputView)
        min = attrs.getInteger(R.styleable.NumberInputView_min, -30)
        max = attrs.getInteger(R.styleable.NumberInputView_max, 30)
        step = attrs.getInteger(R.styleable.NumberInputView_step, 30)
        textSize = attrs.getInteger(R.styleable.NumberInputView_textSize, 30)
        defaultValue = attrs.getInteger(R.styleable.NumberInputView_defaultValue, 0)
        mCurrentNumber = defaultValue
        disable = attrs.getBoolean(R.styleable.NumberInputView_disable, false)
        btnBackground = attrs.getResourceId(R.styleable.NumberInputView_btnBackground, -1)
        attrs.recycle()
    }

    private fun initView(context: Context) {
        //1
        LayoutInflater.from(context).inflate(R.layout.view_number_input,this)
        //2
        LayoutInflater.from(context).inflate(R.layout.view_number_input,this,true)
        //3
        val view = LayoutInflater.from(context).inflate(R.layout.view_number_input,null)
        addView(view)
        mBinding = ViewNumberInputBinding.inflate(LayoutInflater.from(context))
        addView(mBinding?.root)
        mBinding?.apply {
            mNumberEt.textSize = textSize.toFloat()
            mLeftBtn.isEnabled = !disable
            mRightBtn.isEnabled = !disable
        }
        updateNumber()
    }

    private fun setUpEvent() {
        mBinding?.apply {
            mLeftBtn.setOnClickListener {
                mRightBtn.isEnabled = true
                mCurrentNumber -= step
                if (min != 0 && mCurrentNumber <= min) {
                    mCurrentNumber = min
                    mLeftBtn.isEnabled = false
                }
                updateNumber()
            }
            mRightBtn.setOnClickListener {
                mLeftBtn.isEnabled = true
                mCurrentNumber += step
                if (max != 0 && mCurrentNumber >= max) {
                    mCurrentNumber = max
                    mRightBtn.isEnabled = false
                }
                updateNumber()
            }
        }
    }

    fun updateNumber() {
        mBinding?.mNumberEt?.setText(mCurrentNumber.toString())
        mListener?.invoke(mCurrentNumber)
    }

    /**
     * number改变监听
     */
    fun onNumberChange(listener: (Int) -> Unit) {
        mListener = listener
    }

}