package de.lukaseichler.recurrentneuralnetwork

import javax.annotation.{Nonnull, Nullable}

import de.lukaseichler.recurrentneuralnetwork.structure.Layer

import scala.collection.mutable

/**
 * @author Lukas Eichler

 */
class LayerResults {
  private val resultsPerLayer = new mutable.LinkedHashMap[Layer, mutable.Set[Double]] with mutable.MultiMap[Layer, Double]

  def size: Int = {
    resultsPerLayer.keys.size
  }

  def add(@Nullable layer: Layer, @Nonnull results: List[Double]) {
    require(results != null)
    results.foreach(x => resultsPerLayer.addBinding(layer, x))
  }

  def get(@Nullable layer: Layer): Set[Double] = {
    resultsPerLayer.apply(layer).toSet
  }

  @Nullable def getLastResult: List[Double] = {
    if (resultsPerLayer.keySet.size == 0) {
      return null
    }
    resultsPerLayer.apply(resultsPerLayer.keySet.last).toList
  }

  @Nullable def getSecondLastResult: Set[Double] = {
    if (resultsPerLayer.keySet.size < 2) {
      return null
    }
    resultsPerLayer.apply(resultsPerLayer.keySet.toList.reverse(1)).toSet
  }

  @SuppressWarnings(Array("unchecked")) def getReverseValueIterator: Iterator[List[Double]] = {
    val values: List[List[Double]] = resultsPerLayer.keySet.map(x => resultsPerLayer.apply(x).toList).toList
    values.reverseIterator
  }

  def clear() {
    resultsPerLayer.clear()
  }
}
