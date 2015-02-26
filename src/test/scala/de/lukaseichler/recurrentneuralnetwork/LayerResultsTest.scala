package de.lukaseichler.recurrentneuralnetwork

import de.lukaseichler.recurrentneuralnetwork.structure.Layer
import org.scalatest.Matchers
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.{BeforeMethod, Test}

/**
 * @author Lukas Eichler
 */
class LayerResultsTest extends TestNGSuite with Matchers {

  private var results: LayerResults = null

  @BeforeMethod
  def setUp() {
    results = new LayerResults
  }

  @Test
  def addResults() {
    results.add(new Layer, List(1.0, 2.0))
    results.size should be(1)
  }

  @Test
  def addNullLayer() {
    results.add(null, List(1.0))
  }

  @Test(expectedExceptions = Array(classOf[IllegalArgumentException]))
  def addNullValues() {
    results.add(new Layer, null)
  }

  @Test def retrievingData() {
    val layer1: Layer = new Layer
    val layer2: Layer = new Layer
    results.add(layer1, List(1.0))
    results.add(layer2, List(2.0))
    results.get(layer1) should contain(1.0)
    results.get(layer2) should contain(2.0)
  }

  @Test()
  def getWithNullLayer() {
    results.add(null, List(0.0))
    results.get(null) should contain(0.0)
  }

  @Test
  @throws(classOf[Exception])
  def getSecondLastResult() {
    val layer1: Layer = new Layer
    val layer2: Layer = new Layer
    results.add(layer1, List(1.0))
    results.add(layer2, List(2.0))
    val result = results.getSecondLastResult
    result should contain(1.0)
  }

  @Test
  def getLast() {
    val layer1: Layer = new Layer
    val layer2: Layer = new Layer
    results.add(layer1, List(1.0))
    results.add(layer2, List(2.0))
    val result = results.getLastResult
    result should contain(2.0)
  }

  @Test
  def getLastResultWithoutDataPresent() {
    results.getLastResult shouldBe null
  }

  @Test
  def getSecondLastResultWithoutDataPresent() {
    results.getSecondLastResult shouldBe null
  }

  @Test
  def reverseValueIterator() {
    val layer1: Layer = new Layer
    val layer2: Layer = new Layer
    results.add(layer1, List(1.0))
    results.add(layer2, List(2.0))
    val iterator = results.getReverseValueIterator
    iterator.next() should contain(2.0)
    iterator.next() should contain(1.0)
  }

  @Test
  def clear() {
    val layer1: Layer = new Layer
    val layer2: Layer = new Layer
    results.add(layer1, List(1.0))
    results.add(layer2, List(2.0))
    results.clear()
    results.size shouldBe 0
  }
}
