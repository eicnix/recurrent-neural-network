package de.lukaseichler.recurrentneuralnetwork.structure

import javax.annotation.{Nonnull, Nullable}

import de.lukaseichler.recurrentneuralnetwork.activation.{ActivationFunction, LogActivation}

import scala.collection.mutable

/**
 * @author Lukas Eichler

 */
class Node(var activationFunction: ActivationFunction) {
  private var weights: mutable.MutableList[Double] = new mutable.MutableList[Double]()

  def this() = this(new LogActivation())

  require(activationFunction != null)

  def calculate(@Nonnull inputs: Seq[Double]): Double = {
    require(inputs != null)
    var result: Double = 0
    for (i <- inputs.indices) {
      result += inputs(i) * getWeight(i)
    }
    activationFunction.apply(result)
  }

  @Nonnull def getActivationFunction: ActivationFunction = {
    activationFunction
  }

  def setActivationFunction(@Nullable activationFunction: ActivationFunction) {
    this.activationFunction = activationFunction
  }

  def setWeights(@Nonnull weights: Double*) {
    require(weights != null)
    this.weights = new mutable.MutableList[Double]
    this.weights ++= weights
  }

  def updateWeights(@Nonnull deltas: List[Double]): List[Double] = {
    require(deltas != null)
    require(!(weights.size > 0 && weights.size != deltas.size), "Expecting matching the size of the weights used. " + weights.size + " are used and " + deltas.size + " should be applied")
    for (i <- 0 until deltas.size) {
      weights.updated(i, getWeight(i) + deltas(i))
    }
    deltas.zipWithIndex.map { case (x, i) => weights.update(i, getWeight(i) + deltas(i))}
    weights.toList
  }

  private def getWeight(i: Int): Double = {
    if (i < weights.size) {
      return weights(i)
    }
    weights += 1.0
    1
  }
}
