package com.yzzzd.placeholder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.getIntOrThrow
import kotlin.random.Random

/**
 * Created by @yzzzd on 9/18/21.
 */

class PlaceholderTextView: AppCompatTextView {

    private var placeholderRound = 0F
    private var placeholderColor = ContextCompat.getColor(context, R.color.tint_placeholder)

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

        val shortArray = resources.getStringArray(R.array.short_phrase)
        val mediumArray = resources.getStringArray(R.array.medium_phrase)
        val longArray = resources.getStringArray(R.array.long_phrase)

        var randomText = mediumArray[Random.nextInt(mediumArray.size)]

        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.PlaceholderTextView)
            placeholderRound = ta.getDimension(R.styleable.PlaceholderTextView_placeholder_round, 0F)
            placeholderColor = ta.getColor(R.styleable.PlaceholderTextView_placeholder_color, ContextCompat.getColor(context, R.color.tint_placeholder))

            val placeholderLength = ta.getInt(R.styleable.PlaceholderTextView_placeholder_length, MEDIUM)

            randomText = when (placeholderLength) {
                SHORT -> shortArray[Random.nextInt(shortArray.size)]
                MEDIUM -> mediumArray[Random.nextInt(mediumArray.size)]
                else -> longArray[Random.nextInt(longArray.size)]
            }

            val textResources = ta.getResourceId(R.styleable.PlaceholderTextView_placeholder_text, -1)
            if (textResources != -1) {
                val placeholderText = resources.getStringArray(textResources)
                randomText = placeholderText[Random.nextInt(placeholderText.size)]
            }
        }

        text = randomText
    }

    init {
        setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5.0F,  resources.displayMetrics), 1.0F)
        setTextColor(context.getColor(android.R.color.transparent))
    }

    override fun draw(canvas: Canvas?) {
        val currentLayout = layout
        if (currentLayout == null) {
            super.draw(canvas)
            return
        }
        val lineCount = currentLayout.lineCount
        val rect = Rect()
        val paint = Paint()
        paint.color = placeholderColor
        for (i in 0 until lineCount) {
            rect.top = currentLayout.getLineTop(i)
            rect.left = currentLayout.getLineLeft(i).toInt()
            rect.right = currentLayout.getLineRight(i).toInt()
            rect.bottom = currentLayout.getLineBottom(i) - (if (i + 1 == lineCount) 0 else currentLayout.spacingAdd.toInt())

            val rectF = RectF(rect)

            canvas?.drawRoundRect(rectF, placeholderRound, placeholderRound, paint)
        }
        super.draw(canvas)
    }

    companion object {
        const val SHORT = 0
        const val MEDIUM = 1
        const val LONG = 2
    }
}