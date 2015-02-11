package de.lukaseichler.recurrentneuralnetwork.structure;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.google.common.collect.Lists;
import de.lukaseichler.recurrentneuralnetwork.LogActivation;
import de.lukaseichler.recurrentneuralnetwork.NeuralNetworkTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class LayerTest extends NeuralNetworkTest {

    @Test
    public void layerWithoutNodes() throws Exception {
        Layer layer = new Layer();
        assertThat(layer.getNeuronCount()).isEqualTo(0);
    }

    @Test
    public void simpleInput() throws Exception {
        Layer layer = new Layer(1);
        assertThat(layer.calculate(Lists.newArrayList(2.0, 3.0))).containsOnly(applyDefaultActivationFunction(5.0));
    }

    @Test
    public void multipleNodes() throws Exception {
        Layer layer = new Layer(2);
        assertThat(layer.calculate(Lists.newArrayList(2.0, 3.0))).containsOnly(applyDefaultActivationFunction(5.0), applyDefaultActivationFunction(5.0));
    }

    @Test
    public void multipleNodesWithActivationFunction() throws Exception {
        Layer layer = new Layer(2, new LogActivation());
        double value = new LogActivation().apply(5);
        assertThat(layer.calculate(Lists.newArrayList(2.0, 3.0))).containsOnly(value, value);
    }

    @Test
    public void trainLayerWithPreviousWeights() throws Exception {
        Layer layer = new Layer(2);
        assertThat(layer.train(0.3, Arrays.asList(1.3, 1.2), Arrays.asList(1.0, 2.0))).hasSize(4);
    }

    @Test
    public void trainReturnsAllUpdatedWeights() throws Exception {
        Layer layer = new Layer(2);
        assertThat(layer.train(0.3, Arrays.asList(1.0, 1.0))).hasSize(4);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void trainWithNullPreviousWeights() throws Exception {
        Layer layer = new Layer(2);
        assertThat(layer.train(0.3, null, Arrays.asList(1.0, 1.0))).hasSize(4);

    }

    @Test
    public void trainWithoutPreviousWeights() throws Exception {
        Layer layer = new Layer(2);
        assertThat(layer.train(0.3, Arrays.asList(1.0, 1.0))).hasSize(4);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void trainNullInput() throws Exception {
        Layer layer = new Layer(2);
        layer.train(0.3, null, null);
    }
}
