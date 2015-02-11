package de.lukaseichler.recurrentneuralnetwork.structure;

import org.testng.annotations.Test;
import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class InputLayerTest {
    @Test
    public void calculate() throws Exception {
        Layer layer = new InputLayer(2);
        assertThat(layer.calculate(Lists.newArrayList(1.0, 2.0))).containsOnly(1.0, 2.0);
    }
}
