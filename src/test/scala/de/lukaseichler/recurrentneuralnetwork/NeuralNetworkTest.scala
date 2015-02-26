package de.lukaseichler.recurrentneuralnetwork

import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation

/**
 * @author Lukas Eichler
 */
trait NeuralNetworkTest {
  protected def applyDefaultActivationFunction(value: Double): Double = {
    new LogActivation().apply(value)
  }
}
