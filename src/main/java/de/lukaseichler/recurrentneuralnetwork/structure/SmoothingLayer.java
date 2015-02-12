package de.lukaseichler.recurrentneuralnetwork.structure;

import de.lukaseichler.recurrentneuralnetwork.activation.TanhActivation;

/**
 * @author leichler
 */
public class SmoothingLayer extends Layer {

    public SmoothingLayer() {
        this(0);
    }

    public SmoothingLayer(int count) {
        super(count, new TanhActivation());
    }

}
