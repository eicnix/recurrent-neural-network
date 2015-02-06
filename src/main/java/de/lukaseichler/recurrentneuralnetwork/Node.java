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

    public Node(@Nullable ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Node() {

    }

    public double calculate(@Nonnull List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        double result = 0;
        for (int i = 0; i < inputs.size(); i++) {
            double input = inputs.get(i);
            result += input * getWeight(i);
        }
        if (activationFunction != null) {
            return activationFunction.apply(result);
        } else {
            return result;
        }
    }

    private double getWeight(int i) {
        if (i < weights.size()) {
            return weights.get(i);
        }
        return 1;
    }

    public @Nullable ActivationFunction getActivationFunction() {
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
}
