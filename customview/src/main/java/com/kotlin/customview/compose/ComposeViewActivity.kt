package com.kotlin.customview.compose

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kotlin.customview.R
import com.kotlin.customview.databinding.AcyComposeViewBinding
import com.kotlin.customview.etx.e

class ComposeViewActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = AcyComposeViewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.mNumberInputView.onNumberChange {
            "number value change  -> $it".e()
        }
    }
}