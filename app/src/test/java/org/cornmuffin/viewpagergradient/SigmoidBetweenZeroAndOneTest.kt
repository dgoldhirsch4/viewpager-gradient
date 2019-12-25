package org.cornmuffin.viewpagergradient

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SigmoidBetweenZeroAndOneTest {

    @Test
    fun dummy() {
        val inputs = listOf(0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0)

        inputs.forEach {
            val output = ColorComputer.SigmoidBetweenZeroAndOne.compute(it)
            println("$it   $output")
        }
    }

    @Test
    fun testComputeBetweenAboveFiftyPercent() {
        val inputs = listOf(0.6, 0.7, 0.8, 0.9)
        val outputs = inputs.map { ColorComputer.SigmoidBetweenZeroAndOne.compute(it) }

        inputs.forEachIndexed { i, input -> assertTrue(outputs[i] >= input) }
    }

    @Test
    fun testComputeBetweenBelowFiftyPercent() {
        val inputs = listOf(0.1, 0.2, 0.3, 0.4)
        val outputs = inputs.map { ColorComputer.SigmoidBetweenZeroAndOne.compute(it) }

        inputs.forEachIndexed { i, input -> assertTrue(outputs[i] <= input) }
    }

    @Test
    fun testCompute_fiftyPercent() {
        ColorComputer.SigmoidBetweenZeroAndOne.compute(0.5).run {
            assertEquals(0.5, this, 0.0000001)
        }
    }

    @Test
    fun testCompute_oneHundredPercent() {
        ColorComputer.SigmoidBetweenZeroAndOne.compute(1.0).run {
            assertEquals(1.0, this, 0.0000001)
        }
    }

    @Test
    fun testCompute_zeroPercent() {
        ColorComputer.SigmoidBetweenZeroAndOne.compute(0.0).run {
            assertEquals(0.0, this, 0.0000001)
        }
    }
}
