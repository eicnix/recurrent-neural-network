package de.lukaseichler.recurrentneuralnetwork;

import java.util.Arrays;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class NodeTest extends NeuralNetworkTest {

    private Node node;

    @BeforeMethod
    public void setUp() throws Exception {
        node = new Node();
    }

    @Test
    public void inputCalculated() throws Exception {
        assertThat(node.calculate(Lists.newArrayList(1.5, 2.0))).isEqualTo(applyDefaultActivationFunction(3.5));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nullActivationFunction() throws Exception {
        new Node(null);
    }

    @Test
    public void nodeWithActivationFunction() throws Exception {
        node.setActivationFunction(new LogActivation());
        assertThat(node.calculate(Lists.newArrayList(1.0, 2.0))).isEqualTo(applyDefaultActivationFunction(3));
    }

    @Test
    public void logAsDefaultActivationFunction() throws Exception {
        assertThat(node.getActivationFunction()).isInstanceOf(LogActivation.class);
    }

    @Test
    public void weightedInput() throws Exception {
        node.setWeights(2, 3);
        assertThat(node.calculate(Lists.newArrayList(1.0, 1.0))).isEqualTo(applyDefaultActivationFunction(5));
    }


    @Test
    public void weightedInputWithActivationFunction() throws Exception {
        node.setWeights(2, 3);
        node.setActivationFunction(new LogActivation());
        assertThat(node.calculate(Lists.newArrayList(1.0, 1.0))).isEqualTo(applyDefaultActivationFunction(5));
    }

    @Test
    public void weightUpdate() throws Exception {
        node.updateWeights(Arrays.asList(0.3));
        assertThat(node.calculate(Arrays.asList(1.0))).isEqualTo(applyDefaultActivationFunction(1.3));
    }

    @Test
    public void weightsUpdateReturnsUpdatedWeights() throws Exception {
        assertThat(node.updateWeights(Arrays.asList(0.3, 0.5))).containsAll(Arrays.asList(1.3, 1.5));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void weightUpdateNullDeltas() throws Exception {
        node.updateWeights(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void mismatchingWeightsAndDeltas() throws Exception {
        node.calculate(Arrays.asList(1.0, 1.0));
        node.updateWeights(Arrays.asList(1.0));
    }

}
