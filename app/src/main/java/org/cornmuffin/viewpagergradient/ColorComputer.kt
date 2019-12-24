package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.Log
import java.lang.Double.min

class ColorComputer(private val context: Context) {
    private val argbEvaluator = ArgbEvaluator()

    private val delay = 0.20
    private val delayFactor = 1.0 / delay

    private val colorIds = intArrayOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    fun colorAt(position: Int) = context.getColor(colorIds[position % colorIds.size])

    fun colorAt(offset: Float, startPosition: Int, endPosition: Int): Int {
        val effectiveOffset = when {
            offset <= delay -> 0f
            offset >= 1.0 - delay -> 1f
            else -> min((offset - delay) * delayFactor, 1.00).toFloat()
        }

        Log.e("=====", "$offset $effectiveOffset $startPosition")

        return argbEvaluator.evaluate(
            effectiveOffset,
            colorAt(startPosition),
            colorAt(endPosition)
        ) as Int
    }
}
