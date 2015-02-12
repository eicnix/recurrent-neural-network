package de.lukaseichler.recurrentneuralnetwork.datatransformation;

import java.util.Arrays;
import java.util.Comparator;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScaleTest {

    @Test
    public void scaleListOfInputs() throws Exception {
        assertThat(Scale.scale(Arrays.asList(0.0, 5.0, 10.0)))
                .hasSize(3)
                .containsOnly(0.0, 0.5, 1.0);
    }

    @Test
    public void sortResults() throws Exception {
        assertThat(Scale.scale(Arrays.asList(5.0, 0.0, 10.0)))
                .isSortedAccordingTo(Comparator.<Double>naturalOrder());
    }
}