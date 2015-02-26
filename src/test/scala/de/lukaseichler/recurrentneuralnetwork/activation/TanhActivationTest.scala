package de.lukaseichler.recurrentneuralnetwork.activation

import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

import scala.math.tanh

/**
 * @author Lukas Eichler
 */
class TanhActivationTest extends TestNGSuite with Matchers {

  @Test
  def apply() {
    new TanhActivation().apply(3.0) should be(tanh(3.0))
  }
}
