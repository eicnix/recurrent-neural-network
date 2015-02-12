package de.lukaseichler.recurrentneuralnetwork.activation;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TanhActivationTest {

    @Test
    public void apply() throws Exception {
        assertThat(new TanhActivation().apply(3.0)).isEqualTo(Math.tanh(3.0));
    }
}