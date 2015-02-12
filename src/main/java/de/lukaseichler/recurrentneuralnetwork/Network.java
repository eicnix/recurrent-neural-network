package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import de.lukaseichler.recurrentneuralnetwork.activation.ActivationFunction;
import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation;
import de.lukaseichler.recurrentneuralnetwork.structure.InputLayer;
import de.lukaseichler.recurrentneuralnetwork.structure.Layer;

/**
 * @author leichler
 */
public class Network {

    private final List<Layer> layers = new ArrayList<>();
    private final ActivationFunction activationFunction;
    private final LayerResults results = new LayerResults();

    public Network() {
        this(new LogActivation());
    }

    public Network(final @Nonnull ActivationFunction activationFunction) {
        Preconditions.checkNotNull(activationFunction);
        this.activationFunction = activationFunction;
    }

    public @Nonnull Network addLayer(final int nodeCount) {
        if (layers.size() == 0) {
            layers.add(new InputLayer(nodeCount));
        } else {
            layers.add(new Layer(nodeCount, activationFunction));
        }
        return this;
    }

    public double calculateError(final double expected, @Nonnull final List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        double result = calculate(inputs).get(0);
        return (expected - result) * (1 - result) * result;
    }

    public List<Double> calculate(@Nonnull List<Double> inputs) {
        return Iterables.getLast(layers).calculate(calculateState(inputs));
    }

    public List<Double> calculateState(@Nonnull final List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        if (layers.size() < 2) {
            throw new IllegalStateException("Trying to calculate the state for a network that does not have a valid structure");
        }
        List<Double> results = new ArrayList<>();
        inputs.forEach(results::add);
        for (int i = 0, layersSize = layers.size() - 1; i < layersSize; i++) {
            final Layer layer = layers.get(i);
            results = layer.calculate(results);
        }
        return results;
    }

    @SuppressWarnings("ConstantConditions")
    public void train(final double expected, final List<Double> inputs) {
        results.add(null, inputs);
        for (Layer layer : layers) {
            results.add(layer, layer.calculate(results.getLastResult()));
        }
        Iterator<List<Double>> resultsIterator = results.getReverseValueIterator();
        resultsIterator.next(); // skip last result
        List<Double> previousWeights = null;
        for (Layer layer : Lists.reverse(layers)) {
            List<Double> results = resultsIterator.next();
            if (previousWeights == null) {
                previousWeights = layer.train(expected, results);
            } else {
                previousWeights = layer.train(expected, previousWeights, results);
            }
        }
        results.clear();
    }

    public int getHiddenLayerCount() {
        if (layers.size() < 3) {
            return 0;
        }
        return layers.size() - 2;
    }

    public int getInputNeuronCount() {
        if (layers.size() == 0) {
            return 0;
        }
        return layers.get(0).getNeuronCount();
    }

    public int getOutputNeuronCount() {
        if (layers.size() < 2) {
            return 0;
        }
        return Iterables.getLast(layers).getNeuronCount();
    }
}
