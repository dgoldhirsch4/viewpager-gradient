package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.Log
import kotlin.math.pow

object ColorComputer {
    // Null compression => no compression (gradient is linear).
    // Meaningful compression values are all greater than or equal to 0.5.
    // Compression less than 1.0 means that the gradient is closer to being linear.
    // Compression greater than 1.0 means the gradient takes longer to begin but is
    // quicker when it happens.
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

        /**
         * Get the value along a Logistic Function Curve ranging from 0.0 to 1.0,
         * with some control over the steepness of the curve (which we call here
         * 'compression').
         *
         * https://en.wikipedia.org/wiki/Logistic_function
         *
         * This is derived by using the input x as a percentage along the x-axis
         * of a Standard Logistic Function (whose parameters limit the height of the
         * function to 1.0 and for which the function crosses the y-axis at 0.5,
         * and for which the function is essentially zero at -6 and 1 at +6.
         *
         * Given the constants used in the function, the compression will produce
         * reasonable results only if it is greater than or equal to 0.5 (smaller values
         * flatten the curve such that by -6 and +6 it is not close to zero and one, respectively).
         */
        fun compute(x: Double, compression: Double) = when (x) {
            0.0 -> 0.0
            1.0 -> 1.0
            else -> logistic(x * 12.0 - 6.0, compression)
        }

        private fun logistic(x: Double, compression: Double) =
            1.0 / (1.0 + Math.E.pow(-1.0 * compression * x))
    }
}
