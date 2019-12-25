package org.cornmuffin.viewpagergradient

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LogisticBetweenZeroAndOneTest {

    @Test
    fun testComputeBetweenAboveFiftyPercent() {
        val inputs = listOf(0.6, 0.7, 0.8, 0.9)

        REASONABLE_COMPRESSIONS.forEach { c ->
            val outputs = inputs.map { ColorComputer.LogisticBetweenZeroAndOne.compute(it, c) }

            inputs.forEachIndexed { i, input ->
                assertTrue(
                    "given compression $c, ${outputs[i]} was not >= $input",
                    outputs[i] >= input
                )

                assertTrue(
                    "given compression $c, ${outputs[i]} was not <= 1.0",
                    outputs[i] <= 1.0
                )
            }
        }

    }

    @Test
    fun testComputeBetweenBelowFiftyPercent() {
        val inputs = listOf(0.1, 0.2, 0.3, 0.4)

        REASONABLE_COMPRESSIONS.forEach { c ->
            val outputs = inputs.map { ColorComputer.LogisticBetweenZeroAndOne.compute(it, c) }

            inputs.forEachIndexed { i, input ->
                assertTrue(
                    "given compression $c, ${outputs[i]} was not <= $input",
                    outputs[i] <= input
                )

                assertTrue(
                    "given compression $c, ${outputs[i]} was not >= 0.0",
                    outputs[i] >= 0.0
                )
            }
        }
    }

    @Test
    fun testCompute_fiftyPercent() {
        REASONABLE_COMPRESSIONS.forEach { c ->
            ColorComputer.LogisticBetweenZeroAndOne.compute(0.5, c).run {
                assertEquals(0.5, this, 0.0000001)
            }
        }
    }

    @Test
    fun testCompute_oneHundredPercent() {
        REASONABLE_COMPRESSIONS.forEach { c ->
            ColorComputer.LogisticBetweenZeroAndOne.compute(1.0, c).run {
                assertEquals(1.0, this, 0.0000001)
            }
        }
    }

    @Test
    fun testCompute_zeroPercent() {
        REASONABLE_COMPRESSIONS.forEach { c ->
            ColorComputer.LogisticBetweenZeroAndOne.compute(0.0, c).run {
                assertEquals(0.0, this, 0.0000001)
            }
        }
    }

    companion object {
        val REASONABLE_COMPRESSIONS = listOf(0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 2.0, 10.0)
    }
}
