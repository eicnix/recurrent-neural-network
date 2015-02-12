package de.lukaseichler.recurrentneuralnetwork;

import java.util.List;
import javax.annotation.Nonnull;

/**
 * @author leichler
 */
public class RecurrentNeuralNetwork extends Network {

    private List<Double> previousState;

    @Override public List<Double> calculate(@Nonnull final List<Double> input) {
        List<Double> result = super.calculate(input);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i) * getState(i));
        }
        previousState = result;
        return result;
    }

    private Double getState(final int i) {
        if (previousState == null) {
            return 1.0;
        }
        return previousState.get(i);
    }
}
