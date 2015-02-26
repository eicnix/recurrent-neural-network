package de.lukaseichler.recurrentneuralnetwork.structure

import de.lukaseichler.recurrentneuralnetwork.activation.TanhActivation
import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

/**
 * @author leichler
 */
class SmoothingLayerTest extends TestNGSuite with Matchers {

  @Test
  def creatingWithoutParameters(): Unit = {
    new SmoothingLayer().getNeuronCount should be(0)
  }

  @Test
  def creatingWithParameter() {
    val smoothingLayer: SmoothingLayer = new SmoothingLayer(2)
    smoothingLayer.getNeuronCount should be(2)
  }

  @Test
  def activationFunction(): Unit = {
    val smoothingLayer: SmoothingLayer = new SmoothingLayer(2)
    smoothingLayer.getActivationFunction shouldBe a[TanhActivation]
  }

}
