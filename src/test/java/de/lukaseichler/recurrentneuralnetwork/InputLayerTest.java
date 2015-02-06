package de.lukaseichler.recurrentneuralnetwork;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class InputLayerTest {
    @Test
    public void calculate() throws Exception {
        Layer layer = new InputLayer(2);
        assertThat(layer.calculate(1, 2)).containsOnly(1.0, 2.0);
    }
}
