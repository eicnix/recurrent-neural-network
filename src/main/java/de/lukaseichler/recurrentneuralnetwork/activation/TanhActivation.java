package de.lukaseichler.recurrentneuralnetwork.activation;

/**
 * @author leichler
 */
public class TanhActivation implements ActivationFunction {

    @Override public double apply(final double input) {
        return Math.tanh(input);
    }
}
