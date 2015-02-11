package de.lukaseichler.recurrentneuralnetwork;

import java.util.List;
import javax.annotation.Nonnull;

/**
 * @author leichler
 *         TODO split in state and output layer
 */
public class RecurrentNeuralNetwork extends Network {

    private List<Double> state;

    @Override public List<Double> calculate(@Nonnull final List<Double> input) {
        List<Double> result = super.calculate(input);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i) * getState(i));
        }
        state = result;
        return result;
    }

    private Double getState(final int i) {
        if (state == null) {
            return 1.0;
        }
        return state.get(i);
    }
}
