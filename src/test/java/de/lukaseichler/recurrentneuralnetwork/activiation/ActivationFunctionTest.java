package de.lukaseichler.recurrentneuralnetwork.activiation;

import org.testng.annotations.Test;
import de.lukaseichler.recurrentneuralnetwork.activation.ActivationFunction;
import de.lukaseichler.recurrentneuralnetwork.activation.LogActivation;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author leichler
 */
public class ActivationFunctionTest {

    private static final int TEST_VALUE = 3;

    @Test
    public void logActivationFunction() throws Exception {
        ActivationFunction activationFunction = new LogActivation();
        double value = 1 / (1 + Math.pow(Math.E, -TEST_VALUE));
        assertThat(activationFunction.apply(TEST_VALUE)).isEqualTo(value);
    }
}
