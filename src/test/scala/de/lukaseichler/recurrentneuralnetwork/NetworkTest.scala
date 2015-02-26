package de.lukaseichler.recurrentneuralnetwork


import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation
import de.lukaseichler.recurrentneuralnetwork.structure.SmoothingLayer
import org.assertj.core.api.Assertions._
import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test

/**
 * @author Lukas Eichler
 */
class NetworkTest extends TestNGSuite with Matchers with NeuralNetworkTest {
  @Test
  @throws(classOf[Exception])
  def layerStructure {
    val network: Network = new Network
    network.addLayer(2).addLayer(1)
    network.calculate(List(1.0, 2.0, 2.0)) should contain only applyDefaultActivationFunction(5.0)
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def nullActivationFunction {
    new Network(null)
  }

  @Test
  @throws(classOf[Exception])
  def layerStructureWithActivationFunction {
    val network: Network = new Network(new LogActivation)
    network.addLayer(2).addLayer(1)
    val value: Double = new LogActivation().apply(5)
    network.calculate(List(1.0, 2.0, 2.0)) should contain only (value)
  }

  @Test
  @throws(classOf[Exception])
  def calculateError {
    val network: Network = new Network(new LogActivation)
    network.addLayer(2).addLayer(1)
    val error: Double = network.calculateError(1.0, List(1.0, 3.0))
    assertThat(error).isEqualTo(3.176851424509928E-4)
  }

  @Test
  @throws(classOf[Exception])
  def trainNetwork {
    val network: Network = new Network(new LogActivation)
    network.addLayer(2).addLayer(1)
    val inputs: List[Double] = List(4.0, 3.0)
    val error: Double = network.calculateError(3, inputs)
    for (i <- 0 to 100) {
      network.train(network.calculateError(3, inputs), inputs)
    }
    assertThat(error).isGreaterThan(network.calculateError(3, inputs))
  }

  @Test
  @throws(classOf[Exception])
  def hiddenLayersOneLayerStructure {
    val network: Network = new Network
    network.addLayer(2)
    assertThat(network.getHiddenLayerCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def hiddenLayersTwoLayerStructure {
    val network: Network = new Network
    network.addLayer(2)
    network.addLayer(1)
    assertThat(network.getHiddenLayerCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def hiddenLayersThreeLayerStructure {
    val network: Network = new Network
    network.addLayer(2)
    network.addLayer(2)
    network.addLayer(1)
    assertThat(network.getHiddenLayerCount).isEqualTo(1)
  }

  @Test
  @throws(classOf[Exception])
  def addLayerInstance {
    val network: Network = new Network
    network.addLayer(2)
    network.addLayer(2)
    network.addLayer(new SmoothingLayer)
    assertThat(network.getHiddenLayerCount).isEqualTo(1)
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def addNullLayer {
    val network: Network = new Network
    network.addLayer(null)
  }

  @Test
  @throws(classOf[Exception])
  def inputNeuronCountEmptyNetwork {
    val network: Network = new Network
    assertThat(network.getInputNeuronCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def inputNeuronCount {
    val network: Network = new Network
    network.addLayer(3)
    assertThat(network.getInputNeuronCount).isEqualTo(3)
  }

  @Test
  @throws(classOf[Exception])
  def outputNeuronCountEmptyNetwork {
    val network: Network = new Network
    assertThat(network.getOutputNeuronCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def outputNeuronCountOnlyInputLayerNetwork {
    val network: Network = new Network
    network.addLayer(3)
    assertThat(network.getOutputNeuronCount).isEqualTo(0)
  }

  @Test
  @throws(classOf[Exception])
  def outputNeuronCount {
    val network: Network = new Network
    network.addLayer(3)
    network.addLayer(3)
    assertThat(network.getOutputNeuronCount).isEqualTo(3)
  }

  //@Test
  def calculateState {
    val network: Network = new Network
    network.addLayer(3)
    network.addLayer(3)
    network.addLayer(3)
    val state: List[Double] = network.calculateState(List(0.3, 1.0))
    network.calculate(List(0.3, 1.0)) should not equal state
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def calculateStateNullInputs {
    val network: Network = new Network
    network.calculateState(null)
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  @throws(classOf[Exception])
  def calculateStateInvalidNetworkStructure {
    val network: Network = new Network
    network.addLayer(3)
    val state: List[Double] = network.calculateState(List(0.3, 1.0))
    network.calculate(List(0.3, 1.0)) should not equal (state)
  }
}
