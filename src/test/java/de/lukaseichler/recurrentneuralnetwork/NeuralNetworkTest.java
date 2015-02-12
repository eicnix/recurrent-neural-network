package de.lukaseichler.recurrentneuralnetwork;

import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation;

/**
 * @author leichler
 */
public class NeuralNetworkTest {

    protected double applyDefaultActivationFunction(final double value) {
        return new LogActivation().apply(value);
    }

}
