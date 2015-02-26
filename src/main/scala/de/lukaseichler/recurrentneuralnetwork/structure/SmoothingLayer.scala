package de.lukaseichler.recurrentneuralnetwork.structure

import de.lukaseichler.recurrentneuralnetwork.activation.TanhActivation

/**
 * @author Lukas Eichler
 */
class SmoothingLayer(count: Int = 0) extends Layer(count, new TanhActivation) {
  def this() = this(0)
}
