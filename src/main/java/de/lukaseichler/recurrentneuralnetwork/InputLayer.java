package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * @author leichler
 */
public class InputLayer extends Layer {
    public InputLayer(int i) {
        super(i);
    }

    @Override
    public @NotNull List<Double> calculate(@Nullable double... inputs) {
        List<Double> values = new ArrayList<>(inputs.length);
        Arrays.stream(inputs).forEach(values::add);
        return values;
    }
}
