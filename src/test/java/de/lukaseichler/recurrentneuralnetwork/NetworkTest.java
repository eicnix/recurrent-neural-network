package de.lukaseichler.recurrentneuralnetwork;

import org.testng.annotations.Test;

/**
 * @author leichler
 */
public class NetworkTest {

    @Test
    public void layerStructure() throws Exception {
        Network network = new Network();
        network.addLayer(new Layer(2));
        network.addLayer(new Layer(1));
//        assertThat(network.calculate(1, 2, 2)).isEqualTo(5);
    }
}
