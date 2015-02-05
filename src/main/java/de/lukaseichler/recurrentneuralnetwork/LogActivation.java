package de.lukaseichler.recurrentneuralnetwork;

/**
 * @author leichler
 */
public class LogActivation implements ActivationFunction {
    @Override
    public double apply(double input) {
        return 1 / (1 + Math.pow(Math.E, -input));
    }
}
