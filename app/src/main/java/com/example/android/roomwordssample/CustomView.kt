package com.example.android.roomwordssample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val viewModel: WordViewModel

    private val appContext: Context = context.applicationContext


    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.layout_test_model, this, true)

        // 初始化 ViewModel
        val viewModelStoreOwner = if (context is ViewModelStoreOwner) {
            context
        } else {
            throw IllegalArgumentException("Context must implement ViewModelStoreOwner")
        }


        val factory = WordViewModelFactory((appContext as WordsApplication).repository,(appContext as WordsApplication).testRepository)

        viewModel = ViewModelProvider(viewModelStoreOwner, factory)[WordViewModel::class.java]

        val findViewById = findViewById<TextView>(R.id.textview)

        findViewById.text = "这里是测试"

        viewModel.allTestWords.observe(context as LifecycleOwner) {
            val map = it.map { value -> value }
            findViewById.text = "存在：hello ${map.contains("hello")}"
        }
    }
}
