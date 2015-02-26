package de.lukaseichler.recurrentneuralnetwork.activation

/**
 * @author Lukas Eichler

 */
class LogActivation extends ActivationFunction {
  override def apply(input: Double): Double = {
    1 / (1 + Math.pow(Math.E, -input))
  }
}
