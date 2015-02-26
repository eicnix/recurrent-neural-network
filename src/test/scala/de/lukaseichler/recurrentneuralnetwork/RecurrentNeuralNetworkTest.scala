package de.lukaseichler.recurrentneuralnetwork

import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite

/**
 * @author Lukas Eichler
 */
class RecurrentNeuralNetworkTest extends TestNGSuite with Matchers {

  //@Test
  def calculate {
    val network: Network = new RecurrentNeuralNetwork
    network.addLayer(2).addLayer(2).addLayer(1)
    val result: Double = network.calculate(List(1.0, 2.0))(0)
    network.calculate(List(1.0, 2.0))(0) should not equal result
  }
}
