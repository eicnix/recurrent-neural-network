package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.base.Preconditions;

/**
 * @author leichler
 */
public class Layer {
    private final List<Node> nodes;

    public Layer() {
        this(0);
    }

    public Layer(final int nodeCount) {
        this(nodeCount, null);
    }

    public Layer(final int nodeCount, @Nullable final ActivationFunction activationFunction) {
        nodes = new ArrayList<>(nodeCount);
        for (int j = 0; j < nodeCount; j++) {
            nodes.add(new Node(activationFunction));
        }
    }

    public @Nonnull List<Double> calculate(@Nonnull List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        List<Double> results = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            results.add(node.calculate(inputs));
        }
        return results;
    }

    public int getNodeSize() {
        return nodes.size();
    }
}
