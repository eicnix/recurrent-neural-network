package de.lukaseichler.recurrentneuralnetwork;

import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author leichler
 */
public class LayerResults {

    private Multimap<Layer, Double> resultsPerLayer = ArrayListMultimap.create();

    public int size() {
        return resultsPerLayer.keys().size();
    }

    public void add(@Nonnull final Layer layer, @Nonnull final List<Double> results) {
        Preconditions.checkNotNull(layer);
        Preconditions.checkNotNull(results);
        resultsPerLayer.putAll(layer, results);
    }

    public Collection<Double> get(@Nonnull final Layer layer) {
        Preconditions.checkNotNull(layer);
        return resultsPerLayer.get(layer);
    }
}
