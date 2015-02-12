package de.lukaseichler.recurrentneuralnetwork.datatransformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leichler
 */
public class Scale {

    public static List<Double> scale(final List<Double> numbers) {
        final double max = Collections.max(numbers);
        final double min = Collections.min(numbers);
        final double range = max - min;
        List<Double> results = new ArrayList<>(numbers.size());

        results.addAll(numbers.stream().map(d -> (d - min) / range).collect(Collectors.toList()));
        Collections.sort(results);
        return results;
    }
}
