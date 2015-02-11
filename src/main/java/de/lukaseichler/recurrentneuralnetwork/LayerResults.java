package de.lukaseichler.recurrentneuralnetwork;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import de.lukaseichler.recurrentneuralnetwork.structure.Layer;

/**
 * @author leichler
 * TODO improving performance by implementing a custom datastructure
 */
public class LayerResults {

    private final ListMultimap<Layer, Double> resultsPerLayer = MultimapBuilder
            .linkedHashKeys()
            .arrayListValues()
            .build();

    public int size() {
        return resultsPerLayer.keys().size();
    }

    public void add(@Nullable final Layer layer, @Nonnull final List<Double> results) {
        Preconditions.checkNotNull(results);
        resultsPerLayer.putAll(layer, results);
    }

    public Collection<Double> get(@Nonnull final Layer layer) {
        Preconditions.checkNotNull(layer);
        return resultsPerLayer.get(layer);
    }

    public @Nullable List<Double> getLastResult() {
        if (resultsPerLayer.keySet().size() == 0) {
            return null;
        }
        return resultsPerLayer.get(Iterables.getLast(resultsPerLayer.keySet()));
    }

    public @Nullable List<Double> getSecondLastResult() {
        if (resultsPerLayer.keySet().size() < 2) {
            return null;
        }
        return resultsPerLayer.get(Iterables.get(resultsPerLayer.keySet(), resultsPerLayer.keySet().size() - 2, null));
    }

    @SuppressWarnings("unchecked") public Iterator<List<Double>> getReverseValueIterator() {
        LinkedList<List<Double>> values = resultsPerLayer.keySet().stream().map(resultsPerLayer::get).collect(Collectors.toCollection(LinkedList::new));
        return values.descendingIterator();
    }

    public void clear() {
        resultsPerLayer.clear();
    }


}
