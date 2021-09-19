package com.yzzzd.placeholder

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

/**
 * Created by @yzzzd on 9/18/21.
 */

class PlaceholderListView: LinearLayout {

    private var layoutRes = 0
    private var enableAnimation = true

    private var itemHeight = 0

    constructor(context: Context) : super(context) {
        initAttr()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        initAttr(attrs)
    }

    private fun initAttr(attrs: AttributeSet? = null) {

        setWillNotDraw(false)

        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.PlaceholderListView)
            layoutRes = ta.getResourceId(R.styleable.PlaceholderListView_list_item, 0)
            enableAnimation = ta.getBoolean(R.styleable.PlaceholderListView_with_animation, true)
            val oriented = ta.getInt(R.styleable.PlaceholderListView_android_orientation, 1)
            orientation = if (oriented == 1) VERTICAL else HORIZONTAL
        }

        if (enableAnimation) {
            layoutTransition = LayoutTransition()
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if (layoutRes != 0) {
            Handler(Looper.getMainLooper()).postDelayed({
                val viewAdd = LayoutInflater.from(context).inflate(layoutRes, null)

                if (itemHeight < measuredHeight) {
                    addView(viewAdd)
                }

                viewAdd.measure(0, 0)
                val viewHeight = viewAdd.measuredHeight

                itemHeight += viewHeight

            }, if (enableAnimation) 100 else 0)
        }

    }
}