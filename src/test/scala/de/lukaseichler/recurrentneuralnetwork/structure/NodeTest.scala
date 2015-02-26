package de.lukaseichler.recurrentneuralnetwork.structure

import de.lukaseichler.recurrentneuralnetwork.NeuralNetworkTest
import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation
import org.assertj.core.api.Assertions._
import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.{BeforeMethod, Test}

/**
 * @author Lukas Eichler
 */
class NodeTest extends TestNGSuite with Matchers with NeuralNetworkTest {
  private var node: Node = null

  @BeforeMethod
  @throws(classOf[Exception])
  def setUp {
    node = new Node
  }

  @Test
  @throws(classOf[Exception])
  def inputCalculated {
    assertThat(node.calculate(List(1.5, 2.0))).isEqualTo(applyDefaultActivationFunction(3.5))
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def nullActivationFunction {
    new Node(null)
  }

  @Test
  @throws(classOf[Exception])
  def nodeWithActivationFunction {
    node.setActivationFunction(new LogActivation)
    assertThat(node.calculate(List(1.0, 2.0))).isEqualTo(applyDefaultActivationFunction(3))
  }

  @Test
  @throws(classOf[Exception])
  def logAsDefaultActivationFunction {
    node.getActivationFunction.isInstanceOf[LogActivation]
  }

  @Test
  @throws(classOf[Exception])
  def weightedInput {
    node.setWeights(2, 3)
    assertThat(node.calculate(List(1.0, 1.0))).isEqualTo(applyDefaultActivationFunction(5))
  }

  @Test
  @throws(classOf[Exception])
  def weightedInputWithActivationFunction {
    node.setWeights(2, 3)
    node.setActivationFunction(new LogActivation)
    assertThat(node.calculate(List(1.0, 1.0))).isEqualTo(applyDefaultActivationFunction(5))
  }

  @Test
  @throws(classOf[Exception])
  def weightUpdate {
    node.updateWeights(List(0.3))
    assertThat(node.calculate(List(1.0))).isEqualTo(applyDefaultActivationFunction(1.3))
  }

  @Test
  @throws(classOf[Exception])
  def weightsUpdateReturnsUpdatedWeights {
    node.updateWeights(List(0.3, 0.5)) should (contain(1.3) and contain(1.5))
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def weightUpdateNullDeltas {
    node.updateWeights(null)
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def mismatchingWeightsAndDeltas {
    node.calculate(List(1.0, 1.0))
    node.updateWeights(List(1.0))
  }
}
