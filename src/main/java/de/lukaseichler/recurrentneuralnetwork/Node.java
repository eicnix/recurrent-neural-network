package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.base.Preconditions;

/**
 * @author leichler
 */
public class Node {

    private ActivationFunction activationFunction;
    private List<Double> weights = new ArrayList<>();

    public Node() {
        this(new LogActivation());
    }

    public Node(@Nonnull ActivationFunction activationFunction) {
        Preconditions.checkNotNull(activationFunction);

        this.activationFunction = activationFunction;
    }

    public double calculate(@Nonnull List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        double result = 0;
        for (int i = 0; i < inputs.size(); i++) {
            double input = inputs.get(i);
            result += input * getWeight(i);
        }
        return activationFunction.apply(result);
    }

    private double getWeight(int i) {
        if (i < weights.size()) {
            return weights.get(i);
        }
        weights.add(1.0);
        return 1;
    }

    public @Nonnull ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(@Nullable ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void setWeights(@Nonnull double... weights) {
        Preconditions.checkNotNull(weights);

        this.weights = new ArrayList<>(weights.length);
        Arrays.stream(weights).forEach(this.weights::add);
    }

    public List<Double> updateWeights(@Nonnull final List<Double> deltas) {
        Preconditions.checkNotNull(deltas);
        if (weights.size() > 0 && weights.size() != deltas.size()) {
            throw new IllegalArgumentException("Expecting matching the size of the weights used. " + weights.size() + " are used and " + deltas.size() + " should be applied");
        }

        for (int i = 0; i < deltas.size(); i++) {
            weights.set(i, getWeight(i) + deltas.get(i));
        }

        return weights;
    }
}
