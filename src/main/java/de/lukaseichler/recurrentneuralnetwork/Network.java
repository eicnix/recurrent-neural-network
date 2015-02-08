package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.base.Preconditions;

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

    public @Nonnull Network addLayer(int nodeCount) {
        if (layers.size() == 0) {
            layers.add(new InputLayer(nodeCount));
        } else {
            layers.add(new Layer(nodeCount, activationFunction));
        }
        return this;
    }

    public double calculateError(final double expected, @Nonnull final double... inputs) {
        Preconditions.checkNotNull(inputs);
        double result = calculate(inputs).get(0);
        return 0.5 * Math.pow(expected - result, 2);
    }

    public List<Double> calculate(@Nonnull double... input) {
        List<Double> results = new ArrayList<>();
        Arrays.stream(input).forEach(results::add);
        for (Layer layer : layers) {
            results = layer.calculate(results);
        }
        return results;
    }

}
