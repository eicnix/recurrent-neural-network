package de.lukaseichler.recurrentneuralnetwork;

import java.util.Arrays;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class LayerResultsTest {

    @Test
    public void addResults() throws Exception {
        LayerResults results = new LayerResults();
        results.add(new Layer(), Arrays.asList(1.0, 2.0));
        assertThat(results.size()).isEqualTo(2);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullLayer() throws Exception {
        LayerResults results = new LayerResults();
        results.add(null, Arrays.asList(1.0));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullValues() throws Exception {
        LayerResults results = new LayerResults();
        results.add(new Layer(), null);
    }

    @Test
    public void retrievingData() {
        LayerResults results = new LayerResults();
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        results.add(layer1, Arrays.asList(1.0));
        results.add(layer2, Arrays.asList(2.0));

        assertThat(results.get(layer1)).containsOnly(1.0);
        assertThat(results.get(layer2)).containsOnly(2.0);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void getWithNullLayer() throws Exception {
        new LayerResults().get(null);
    }
}
