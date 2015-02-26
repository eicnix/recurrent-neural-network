package de.lukaseichler.recurrentneuralnetwork.datatransformation

import scala.collection.mutable

/**
 * @author leichler
 */
object Scale {
  /**
   * Scales a list of double values onto a interval of [0,1]
   * @param numbers list of numbers to be scaled
   * @return scaled values
   */
  def scale(numbers: List[Double]): mutable.Seq[Double] = {
    val max = numbers.max
    val min = numbers.min
    val range = max - min
    val results = new mutable.MutableList[Double]

    results ++= numbers.map(x => (x - min) / range)
    results.sorted
  }
}


