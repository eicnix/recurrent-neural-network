package de.lukaseichler.recurrentneuralnetwork.activation

import org.assertj.core.api.Assertions._
import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

/**
 * @author Lukas Eichler
 */
class LogActivationTest extends TestNGSuite with Matchers {

  @Test
  def logActivationFunction() {
    val activationFunction: ActivationFunction = new LogActivation
    val value: Double = 1 / (1 + Math.pow(Math.E, -3))
    assertThat(activationFunction.apply(3)).isEqualTo(value)
  }
}
