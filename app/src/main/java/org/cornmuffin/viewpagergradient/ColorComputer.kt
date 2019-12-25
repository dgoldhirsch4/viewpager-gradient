package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import kotlin.math.pow

object ColorComputer {
    var useCompression: Boolean = true

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

        val effectiveOffset = if (useCompression) {
            SigmoidBetweenZeroAndOne.compute(offset.toDouble()).toFloat()
        } else {
            offset
        }

        return argbEvaluator.evaluate(
            effectiveOffset,
            colorAt(startPosition, context),
            colorAt(endPosition, context)
        ) as Int
    }

    object SigmoidBetweenZeroAndOne {
        private const val COMPRESSION = 0.5

        fun compute(x: Double) = when (x) {
            0.0 -> 0.0
            1.0 -> 1.0
            else -> sigmoid(x * 12.0 - 6.0)
        }

        // https://en.wikipedia.org/wiki/Sigmoid_function
        private fun sigmoid(x: Double) = 1.0 / (1.0 + Math.E.pow(-1.0 * COMPRESSION * x))
    }
}
