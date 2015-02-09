package de.lukaseichler.recurrentneuralnetwork;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class NetworkTest extends NeuralNetworkTest {

    @Test
    public void layerStructure() throws Exception {
        Network network = new Network();
        network.addLayer(2)
                .addLayer(1);
        assertThat(network.calculate(Arrays.asList(1.0, 2.0, 2.0))).containsOnly(applyDefaultActivationFunction(5.0));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void nullActivationFunction() throws Exception {
        new Network(null);
    }

    @Test
    public void layerStructureWithActivationFunction() throws Exception {
        Network network = new Network(new LogActivation());
        network.addLayer(2)
                .addLayer(1);
        double value = new LogActivation().apply(5);
        assertThat(network.calculate(Arrays.asList(1.0, 2.0, 2.0))).containsOnly(value);
    }

    @Test
    public void calculateError() throws Exception {
        Network network = new Network(new LogActivation());
        network.addLayer(2)
                .addLayer(1);
        double error = network.calculateError(1.0, Arrays.asList(1.0, 3.0));
        assertThat(error).isEqualTo(3.176851424509928E-4);
    }

    @Test
    public void trainNetwork() throws Exception {
        Network network = new Network(new LogActivation());
        network
                .addLayer(2)
                .addLayer(1);
        List<Double> inputs = Arrays.asList(4.0, 3.0);
        double error = network.calculateError(3, inputs);
        for (int i = 0; i < 100; i++) {
            network.train(network.calculateError(3, inputs), inputs);
        }
        System.out.println(network.calculateError(3, inputs));
        assertThat(error).isGreaterThan(network.calculateError(3, inputs));
    }
}


