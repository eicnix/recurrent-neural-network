package de.lukaseichler.recurrentneuralnetwork.structure;

import org.testng.annotations.Test;
import de.lukaseichler.recurrentneuralnetwork.activation.TanhActivation;

import static org.assertj.core.api.Assertions.assertThat;

public class SmoothingLayerTest {

    @Test
    public void creatingWithoutParameter() throws Exception {
        assertThat(new SmoothingLayer().getNeuronCount()).isEqualTo(0);
    }

    @Test
    public void creatingWithParameter() throws Exception {
        SmoothingLayer smoothingLayer = new SmoothingLayer(2);
        assertThat(smoothingLayer.getNeuronCount()).isEqualTo(2);
    }

    @Test
    public void activationFunction() throws Exception {
        SmoothingLayer smoothingLayer = new SmoothingLayer(2);
        assertThat(smoothingLayer.getActivationFunction()).hasSameClassAs(new TanhActivation());
    }
}