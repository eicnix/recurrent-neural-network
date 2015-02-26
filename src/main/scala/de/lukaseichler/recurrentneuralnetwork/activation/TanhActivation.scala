package de.lukaseichler.recurrentneuralnetwork.activation

/**
 * @author Lukas Eichler

 */
class TanhActivation extends ActivationFunction {
  override def apply(input: Double): Double = {
    math.tanh(input)
  }
}
