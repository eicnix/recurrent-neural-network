package de.lukaseichler.recurrentneuralnetwork;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class NodeTest {

    private Node node;

    @BeforeMethod
    public void setUp() throws Exception {
        node = new Node();
    }

    @Test
    public void inputCalculated() throws Exception {
        assertThat(node.calculate(Lists.newArrayList(1.5, 2.0))).isEqualTo(3.5);
    }

    @Test
    public void nodeWithActivationFunction() throws Exception {
        node.setActivationFunction(new LogActivation());
        assertThat(node.calculate(Lists.newArrayList(1.0, 2.0))).isEqualTo(new LogActivation().apply(3));
    }

    @Test
    public void weightedInput() throws Exception {
        node.setWeights(2, 3);
        assertThat(node.calculate(Lists.newArrayList(1.0, 1.0))).isEqualTo(5);
    }

    @Test
    public void weightedInputWithActivationFunction() throws Exception {
        node.setWeights(2, 3);
        node.setActivationFunction(new LogActivation());
        assertThat(node.calculate(Lists.newArrayList(1.0, 1.0))).isEqualTo(new LogActivation().apply(5));
    }
}
