package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.List;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * @author leichler
 */
public class Layer {
    private final List<Node> nodes;

    public Layer(@NotNull final int nodeCount,@Nullable final ActivationFunction activationFunction) {
        nodes = new ArrayList<>(nodeCount);
        for (int j = 0; j < nodeCount; j++) {
            nodes.add(new Node(activationFunction));
        }
    }

    public Layer(@NotNull final int nodeCount) {
        this(nodeCount, null);
    }

    public @NotNull List<Double> calculate(@Nullable double... inputs) {
        List<Double> results = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            results.add(node.calculate(inputs));
        }
        return results;
    }
}
