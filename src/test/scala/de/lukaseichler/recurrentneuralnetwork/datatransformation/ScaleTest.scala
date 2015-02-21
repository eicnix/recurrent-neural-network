package de.lukaseichler.recurrentneuralnetwork.datatransformation

import java.util
import java.util.Comparator

import org.assertj.core.api.Assertions._
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test


/**
 * @author leichler
 */
class ScaleTest extends TestNGSuite {


    @Test
    def scaleListOfInputs() {
        val result = Scale.scale(util.Arrays.asList(0.0, 5.0, 10.0))
        assertThat(result).containsOnly(0.0, 0.5, 1.0)
    }

    @Test
    def sortResults() {
        assertThat(Scale.scale(util.Arrays.asList(5.0, 0.0, 10.0))).isSortedAccordingTo(Comparator.naturalOrder())
    }
}
