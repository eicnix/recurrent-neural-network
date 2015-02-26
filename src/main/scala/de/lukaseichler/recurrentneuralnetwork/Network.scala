package de.lukaseichler.recurrentneuralnetwork

import javax.annotation.Nonnull

import de.lukaseichler.recurrentneuralnetwork.activation.{ActivationFunction, LogActivation}
import de.lukaseichler.recurrentneuralnetwork.structure.{InputLayer, Layer}

import scala.collection.mutable

/**
 * @author Lukas Eichler
 */
class Network(activationFunction: ActivationFunction) {
  private val layers: mutable.MutableList[Layer] = new mutable.MutableList[Layer]
  private val results: LayerResults = new LayerResults

  def this() = this(new LogActivation())

  require(activationFunction != null)

  @Nonnull def addLayer(nodeCount: Int): Network = {
    if (layers.size == 0) {
      layers += new InputLayer(nodeCount)
    }
    else {
      layers += new Layer(nodeCount, activationFunction)
    }
    this
  }

  @Nonnull def addLayer(@Nonnull layer: Layer): Network = {
    require(layer != null)
    layers += layer
    this
  }

  @SuppressWarnings(Array("ConstantConditions")) def train(expected: Double, inputs: List[Double]) {
    results.add(null, inputs)
    layers.foreach(x => results.add(x, x.calculate(results.getLastResult)))

    val resultsIterator: Iterator[List[Double]] = results.getReverseValueIterator
    resultsIterator.next() // skip last result
    var previousWeights: List[Double] = null
    for (layer <- layers.reverse.tail) {
      val results: List[Double] = resultsIterator.next()
      if (previousWeights == null) {
        previousWeights = layer.train(expected, results)
      }
      else {
        previousWeights = layer.train(expected, previousWeights, results)
      }
    }
    results.clear()
    calculateError(expected, inputs)
  }

  def calculateError(expected: Double, @Nonnull inputs: List[Double]): Double = {
    require(inputs != null)

    val result: Double = calculate(inputs)(0)
    (expected - result) * (1 - result) * result
  }

  def calculate(@Nonnull inputs: List[Double]): List[Double] = {
    calculateState(inputs)
  }

  def calculateState(@Nonnull inputs: List[Double]): List[Double] = {
    require(inputs != null)
    require(layers.size >= 2, "Trying to calculate the state for a network that does not have a valid structure")

    var results = inputs
    layers.foreach(x => results = x.calculate(results))
    results
  }

  def getHiddenLayerCount: Int = {
    if (layers.size < 3) {
      return 0
    }
    layers.size - 2
  }

  def getInputNeuronCount: Int = {
    if (layers.size == 0) {
      return 0
    }
    layers(0).getNeuronCount
  }

  def getOutputNeuronCount: Int = {
    if (layers.size < 2) {
      return 0
    }
    layers.last.getNeuronCount
  }
}
