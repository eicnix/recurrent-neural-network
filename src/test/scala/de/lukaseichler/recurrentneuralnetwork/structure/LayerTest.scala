package de.lukaseichler.recurrentneuralnetwork.structure

import de.lukaseichler.recurrentneuralnetwork.NeuralNetworkTest
import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation
import org.assertj.core.api.Assertions._
import org.scalatest.testng.TestNGSuite
import org.scalatest.{Ignore, Matchers}
import org.testng.annotations.Test

/**
 * @author Lukas Eichler
 */
class LayerTest extends TestNGSuite with Matchers with NeuralNetworkTest {

  @Test
  @throws(classOf[Exception])
  def layerWithoutNodes {
    val layer: Layer = new Layer
    assertThat(layer.getNeuronCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def simpleInput {
    val layer: Layer = new Layer(1)
    layer.calculate(List(2.0, 3.0)) should contain only (applyDefaultActivationFunction(5.0))
  }

  @Test
  @throws(classOf[Exception])
  def instantiationWithoutParameterLogActivation {
    val layer: Layer = new Layer
    layer.getActivationFunction.isInstanceOf[LogActivation]
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def nullActivationFunction {
    val layer: Layer = new Layer(0, null)
  }

  @Test
  @throws(classOf[Exception])
  def multipleNodes {
    val layer: Layer = new Layer(2)
    layer.calculate(List(2.0, 3.0)) should contain only (applyDefaultActivationFunction(5.0))
  }

  @Test
  @throws(classOf[Exception])
  def multipleNodesWithActivationFunction {
    val layer: Layer = new Layer(2, new LogActivation)
    val value: Double = new LogActivation().apply(5)
    layer.calculate(List(2.0, 3.0)) should contain only (value)
  }

  @Test
  @throws(classOf[Exception])
  def trainLayerWithPreviousWeights {
    val layer: Layer = new Layer(2)
    layer.train(0.3, List(1.3, 1.3), List(1.0, 2.0)) should have size 4
  }

  @Test
  @throws(classOf[Exception])
  def trainReturnsAllUpdatedWeights {
    val layer: Layer = new Layer(2)
    layer.train(0.3, List(1.0, 1.0)) should have size 4
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  @Ignore
  def trainWithNullPreviousWeights {
    val layer: Layer = new Layer(2)
    layer.train(0.3, null, List(1.0, 1.0)) should have size 4
  }

  @Test
  @throws(classOf[Exception])
  def trainWithoutPreviousWeights {
    val layer: Layer = new Layer(2)
    layer.train(0.3, List(1.0, 1.0)) should have size 4
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def trainNullInput {
    val layer: Layer = new Layer(2)
    layer.train(0.3, null, null)
  }
}
