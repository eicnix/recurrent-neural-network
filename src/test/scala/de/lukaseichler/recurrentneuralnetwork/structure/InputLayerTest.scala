package de.lukaseichler.recurrentneuralnetwork.structure

import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

/**
 * @author Lukas Eichler
 */
class InputLayerTest extends TestNGSuite with Matchers {

  @Test
  def calculate {
    val layer: Layer = new InputLayer(2)
    layer.calculate(List(1.0, 2.0)) should contain only(1.0, 2.0)
  }
}
