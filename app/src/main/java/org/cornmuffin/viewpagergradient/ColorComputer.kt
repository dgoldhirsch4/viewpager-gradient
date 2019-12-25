package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.Log
import kotlin.math.pow

object ColorComputer {
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

    fun colorAt(
        offset: Float,
        startPosition: Int,
        endPosition: Int,
        context: Context
    ): Int {

        val compressedOffset = compression?.let {
            SigmoidBetweenZeroAndOne.compute(offset.toDouble(), it).toFloat()
        } ?: offset

        Log.e(
            "ColorComputer",
            "compression: $compression offset: $offset compressedOffset: $compressedOffset"
        )

        return argbEvaluator.evaluate(
            compressedOffset,
            colorAt(startPosition, context),
            colorAt(endPosition, context)
        ) as Int
    }

    object SigmoidBetweenZeroAndOne {
        fun compute(x: Double, compression: Double) = when (x) {
            0.0 -> 0.0
            1.0 -> 1.0
            else -> sigmoid(x * 12.0 - 6.0, compression)
        }

        // https://en.wikipedia.org/wiki/Sigmoid_function
        private fun sigmoid(x: Double, compression: Double) =
            1.0 / (1.0 + Math.E.pow(-1.0 * compression * x))
    }
}
