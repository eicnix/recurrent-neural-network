package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * @author leichler
 */
public class Network {

    private List<Layer> layers = new ArrayList<>();
    private ActivationFunction activationFunction;

    public Network() {
        this(null);
    }

    public Network(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public List<Double> calculate(@Nonnull double... input) {
        List<Double> results = new ArrayList<>();
        Arrays.stream(input).forEach(results::add);
        for (Layer layer : layers) {
            results = layer.calculate(results);
        }
        return results;
    }

    public @Nonnull Network addLayer(int nodeCount) {
        if (layers.size() == 0) {
            layers.add(new InputLayer(nodeCount));
        } else {
            layers.add(new Layer(nodeCount, activationFunction));
        }
        return this;
    }
}
