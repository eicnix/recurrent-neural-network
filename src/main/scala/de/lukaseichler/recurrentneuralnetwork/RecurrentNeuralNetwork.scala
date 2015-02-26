package de.lukaseichler.recurrentneuralnetwork

import javax.annotation.Nonnull

/**
 * @author Lukas Eichler
 */
class RecurrentNeuralNetwork extends Network {
  private var previousState: List[Double] = null

  override def calculate(@Nonnull input: List[Double]): List[Double] = {
    val result: List[Double] = super.calculate(input)
    input.zipWithIndex.foreach { case (x, i) => result.updated(i, result(i) * getState(i))}
    previousState = result
    result
  }

  private def getState(i: Int): Double = {
    if (previousState == null) {
      return 1.0
    }

    previousState(i)
  }
}
