package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leichler
 */
public class Node {

    private ActivationFunction activationFunction;
    private List<Double> weights = new ArrayList<>();

    public Node(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Node() {

    }

    public double calculate(double... inputs) {
        double result = 0;
        for (int i = 0; i < inputs.length; i++) {
            double input = inputs[i];
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

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void setWeights(double... weights) {
        this.weights = new ArrayList<>(weights.length);
        Arrays.stream(weights).forEach(this.weights::add);
    }
}
