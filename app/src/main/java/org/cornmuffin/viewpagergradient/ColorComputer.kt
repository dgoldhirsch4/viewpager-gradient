package org.cornmuffin.viewpagergradient

import android.animation.ArgbEvaluator
import android.content.Context

object ColorComputer {
    private val argbEvaluator = ArgbEvaluator()

    private val colorIds = intArrayOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    fun colorAt(position: Int, context: Context) = context.getColor(colorIds[position % colorIds.size])

    fun colorAt(offset: Float, startPosition: Int, endPosition: Int, context: Context) =
        argbEvaluator.evaluate(
            offset,
            colorAt(startPosition, context),
            colorAt(endPosition, context)
        ) as Int
}
