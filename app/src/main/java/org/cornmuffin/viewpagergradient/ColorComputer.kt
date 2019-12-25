package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.Log
import kotlin.math.pow

class ColorComputer(private val context: Context, private val isCompressed:Boolean = false) {
    private val argbEvaluator = ArgbEvaluator()

    private val colorIds = intArrayOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    fun colorAt(position: Int) = context.getColor(colorIds[position % colorIds.size])

    fun colorAt(offset: Float, startPosition: Int, endPosition: Int): Int {
        val effectiveOffset =
            if (isCompressed) sigmoidBetweenZeroAndOne(offset.toDouble()).toFloat() else offset

        Log.e("=====", "$offset $effectiveOffset $startPosition")

        return argbEvaluator.evaluate(
            effectiveOffset,
            colorAt(startPosition),
            colorAt(endPosition)
        ) as Int
    }

    private fun sigmoidBetweenZeroAndOne(x: Double) = when (x) {
        0.0 -> 0.0
        1.0 -> 1.0
        else -> sigmoid(x * 12.0 - 6.0)
    }

    // https://en.wikipedia.org/wiki/Sigmoid_function
    private fun sigmoid(x: Double) = 1.0 / (1.0 + Math.E.pow(-1.0 * x))
}
