package org.cornmuffin.viewpagergradient

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun sigmoid() {
        (0..10).forEach {
            val offset = it / 10.0
            println("$offset ${sigmoidBetweenZeroAndOne(offset)}")
        }
    }

    private fun sigmoidBetweenZeroAndOne(x: Double) = when {
        x == 0.0 -> 0.0
        x == 1.0 -> 1.0
        else -> sigmoid(x * 12.0 - 6.0)
    }

    private fun sigmoid(x: Double) = 1.0 / (1.0 + Math.pow(Math.E, -1.0 * x))
}
