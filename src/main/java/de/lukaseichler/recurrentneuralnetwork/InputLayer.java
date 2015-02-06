package de.lukaseichler.recurrentneuralnetwork;

import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.base.Preconditions;

/**
 * @author leichler
 */
public class InputLayer extends Layer {
    public InputLayer(int i) {
        super(i);
    }

    @Override
    public @Nonnull List<Double> calculate(@Nonnull List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        return inputs;
    }
}
