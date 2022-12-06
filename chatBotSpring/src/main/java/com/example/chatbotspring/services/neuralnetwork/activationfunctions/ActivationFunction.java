package com.example.chatbotspring.services.neuralnetwork.activationfunctions;

import org.ejml.simple.SimpleMatrix;

public interface ActivationFunction {

    final String RELU = "RELU";

    final String SIGMOID = "SIGMOID";

    SimpleMatrix applyActivationFunctionToMatrix(SimpleMatrix input);

    SimpleMatrix applyDerivativeOfActivationFunctionToMatrix(SimpleMatrix input);

    String getName();

}
