package de.lukaseichler.recurrentneuralnetwork;

/**
 * @author leichler
 */
public class NeuralNetworkTest {

    protected double applyDefaultActivationFunction(final double value) {
        return new LogActivation().apply(value);
    }

}
