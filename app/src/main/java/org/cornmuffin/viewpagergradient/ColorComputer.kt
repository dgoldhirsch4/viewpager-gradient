package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.Log
import kotlin.math.pow

object ColorComputer {
    // Well-behaved values must be >= 0.5.
    // Less than 1.0 eases the compression (less noticeable).
    // 1.0 and higher makes the compression very noticeable.
    // If null, there is no compression--the raw offsets are linear.
    var compression: Double? = null

    private val argbEvaluator = ArgbEvaluator()

    private val colorIds = intArrayOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    fun colorAt(position: Int, context: Context) =
        context.getColor(colorIds[position % colorIds.size])

    fun colorAt(rawOffset: Float, startPosition: Int, endPosition: Int, context: Context): Int {
        val offset = compression?.let {
            LogisticBetweenZeroAndOne.compute(rawOffset.toDouble(), it).toFloat()
        } ?: rawOffset

        Log.e(
            "ColorComputer", "rawOffset: $rawOffset offset: $offset"
        )

        return argbEvaluator.evaluate(
            offset,
            colorAt(startPosition, context),
            colorAt(endPosition, context)
        ) as Int
    }

    object LogisticBetweenZeroAndOne {
        fun compute(x: Double, compression: Double) = when (x) {
            0.0 -> 0.0
            1.0 -> 1.0
            else -> logistic(x * 12.0 - 6.0, compression)
        }

        // https://en.wikipedia.org/wiki/Logistic_function
        private fun logistic(x: Double, compression: Double) =
            1.0 / (1.0 + Math.E.pow(-1.0 * compression * x))
    }
}
