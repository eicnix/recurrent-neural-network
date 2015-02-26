package de.lukaseichler.recurrentneuralnetwork.structure

import javax.annotation.Nonnull

/**
 * @author Lukas Eichler

 */
class InputLayer(count: Int) extends Layer(count) {

  @Nonnull override def calculate(@Nonnull inputs: List[Double]): List[Double] = {
    require(inputs != null)
    inputs
  }
}
