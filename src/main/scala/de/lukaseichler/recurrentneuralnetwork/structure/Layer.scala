package de.lukaseichler.recurrentneuralnetwork.structure


import javax.annotation.Nonnull

import de.lukaseichler.recurrentneuralnetwork.activation.{ActivationFunction, LogActivation}

import scala.collection.mutable
import scala.collection.mutable._

/**
 * @author Lukas Eichler
 */
class Layer(nodeCount: Int, activationFunction: ActivationFunction) {
  private val nodes: MutableList[Node] = new MutableList()

  def this(nodeCount: Int) = this(nodeCount, new LogActivation().asInstanceOf[ActivationFunction])

  def this() = this(0)

  require(activationFunction != null)
  for (i <- 1 to nodeCount) {
    nodes += new Node(activationFunction)
  }

  def getNeuronCount: Int = {
    nodes.size
  }

  def train(error: Double, @Nonnull previousWeights: List[Double], @Nonnull inputs: List[Double]): List[Double] = {
    require(previousWeights != null)
    require(inputs != null)

    val deltas = nodes.zipWithIndex.map { case (x, i) => inputs(i) * previousWeights(i) * error}.toList
    trainNodes(deltas)
  }

  def train(error: Double, @Nonnull inputs: List[Double]): List[Double] = {
    require(inputs != null)
    trainNodes(inputs.map(x => x * error))
  }

  private def trainNodes(@Nonnull deltas: List[Double]): List[Double] = {
    var results: mutable.MutableList[Double] = new MutableList()
    nodes.map(x => results ++= x.updateWeights(deltas))
    results.toList
  }

  @Nonnull def calculate(@Nonnull inputs: List[Double]): List[Double] = {
    require(inputs != null)
    nodes.map(x => x.calculate(inputs)).toList
  }

  @Nonnull def getActivationFunction: ActivationFunction = {
    activationFunction
  }
}
