package de.lukaseichler.recurrentneuralnetwork.activation

/**
 * @author Lukas Eichler

 */
trait ActivationFunction {
  def apply(input: Double): Double
}
