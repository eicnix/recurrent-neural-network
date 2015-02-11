package de.lukaseichler.recurrentneuralnetwork;

import java.util.Arrays;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class RecurrentNeuralNetworkTest {
    @Test
    public void calculate() throws Exception {
        Network network = new RecurrentNeuralNetwork();
        network.addLayer(2)
                .addLayer(1);
        double result = network.calculate(Arrays.asList(1.0, 2.0)).get(0);
        assertThat(network.calculate(Arrays.asList(1.0, 2.0)).get(0)).isNotEqualTo(result);
    }
}
