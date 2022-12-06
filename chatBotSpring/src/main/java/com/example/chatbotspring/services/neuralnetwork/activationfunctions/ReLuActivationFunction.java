package com.example.chatbotspring.services.neuralnetwork.activationfunctions;

import org.ejml.simple.SimpleMatrix;

public class ReLuActivationFunction implements ActivationFunction{

    private static final String NAME = "RELU";

    @Override
    public SimpleMatrix applyActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
        for (int i = 0; i < input.numRows(); i++) {
            // only = 0 vì có 1 cột input
            double value = input.get(i, 0);
            double result = value > 0 ? value : (value * 0.1);
            output.set(i, 0, result);
        }
        return output;
    }

    @Override
    public SimpleMatrix applyDerivativeOfActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
        for (int i = 0; i < input.numRows(); i++) {
            double value = input.get(i, 0);
            double result = value > 0 ? 1 : 0.1;
            output.set(i, 0, result);
        }
        // for input > 0: 1, else 0
        return output;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
