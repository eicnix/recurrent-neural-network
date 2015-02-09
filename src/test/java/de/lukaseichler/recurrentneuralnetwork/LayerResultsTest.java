package de.lukaseichler.recurrentneuralnetwork;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class LayerResultsTest {

    private LayerResults results;

    @BeforeMethod
    public void setUp() throws Exception {
        results = new LayerResults();
    }

    @Test
    public void addResults() throws Exception {
        results.add(new Layer(), Arrays.asList(1.0, 2.0));
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void addNullLayer() throws Exception {
        results.add(null, Arrays.asList(1.0));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullValues() throws Exception {
        results.add(new Layer(), null);
    }

    @Test
    public void retrievingData() {
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

    @Test
    public void getSecondLastResult() throws Exception {
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        results.add(layer1, Arrays.asList(1.0));
        results.add(layer2, Arrays.asList(2.0));

        Collection<Double> result = results.getSecondLastResult();
        assertThat(result).containsOnly(1.0)
                .isInstanceOf(List.class);
    }

    @Test
    public void getLast() throws Exception {
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        results.add(layer1, Arrays.asList(1.0));
        results.add(layer2, Arrays.asList(2.0));

        Collection<Double> result = results.getLastResult();
        assertThat(result).containsOnly(2.0)
                .isInstanceOf(List.class);
    }

    @Test
    public void getLastResultWithoutDataPresent() throws Exception {
        assertThat(results.getLastResult()).isNull();
    }

    @Test
    public void getSecondLastResultWithoutDataPresent() throws Exception {
        assertThat(results.getSecondLastResult()).isNull();
    }

    @Test
    public void reverseValueIterator() throws Exception {
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        results.add(layer1, Arrays.asList(1.0));
        results.add(layer2, Arrays.asList(2.0));

        Iterator<List<Double>> iterator = results.getReverseValueIterator();

        assertThat(iterator.next()).containsOnly(2.0);
        assertThat(iterator.next()).containsOnly(1.0);
    }

    @Test
    public void clear() throws Exception {
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        results.add(layer1, Arrays.asList(1.0));
        results.add(layer2, Arrays.asList(2.0));
        results.clear();

        assertThat(results.size()).isEqualTo(0);
    }
}
