package de.lukaseichler.recurrentneuralnetwork.datatransformation

import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

/**
 * @author leichler
 */
class ScaleTest extends TestNGSuite with Matchers {

    @Test
    def scaleListOfInputs() {
      val result = Scale.scale(List(0.0, 5.0, 10.0))
      result should (contain(0.0) and contain(0.5) and contain(1))
    }

  @Test
  def sortResults() {
    val result = Scale.scale(List(0.0, 5.0, 10.0))
    result shouldBe sorted
  }
}