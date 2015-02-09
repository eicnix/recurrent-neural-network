package de.lukaseichler.recurrentneuralnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
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
        this(nodeCount, new LogActivation());
    }

    public Layer(final int nodeCount, @Nonnull final ActivationFunction activationFunction) {
        nodes = new ArrayList<>(nodeCount);
        for (int j = 0; j < nodeCount; j++) {
            nodes.add(new Node(activationFunction));
        }
    }

    public int getNodeSize() {
        return nodes.size();
    }

    public List<Double> train(final double error, final List<Double> previousWeights, final List<Double> inputs) {
        List<Double> updatedWeights = new ArrayList<>();
        List<Double> results = calculate(inputs);
        List<Double> deltas = new ArrayList<>();
        if (previousWeights == null) {
            inputs.forEach(y -> deltas.add(y * error));
        } else {
            for (int i = 0; i < inputs.size(); i++) {
                deltas.add(inputs.get(i) * previousWeights.get(i) * error);
            }
        }
        for (Node node : nodes) {
            updatedWeights.addAll(node.updateWeights(deltas));
        }
        return updatedWeights;
    }

    public @Nonnull List<Double> calculate(@Nonnull List<Double> inputs) {
        Preconditions.checkNotNull(inputs);
        List<Double> results = new ArrayList<>(nodes.size());
        results.addAll(nodes.stream().map(node -> node.calculate(inputs)).collect(Collectors.toList()));
        return results;
    }
}
