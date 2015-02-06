package de.lukaseichler.recurrentneuralnetwork;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class NetworkTest {

    @Test
    public void layerStructure() throws Exception {
        Network network = new Network();
        network.addLayer(2)
                .addLayer(1);
        assertThat(network.calculate(1, 2, 2)).containsOnly(5.0);
    }
}
