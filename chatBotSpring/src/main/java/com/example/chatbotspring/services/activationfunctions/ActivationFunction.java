package com.example.chatbotspring.services.activationfunctions;

import org.ejml.simple.SimpleMatrix;

public interface ActivationFunction {

    final String RELU = "RELU";

    SimpleMatrix applyActivationFunctionToMatrix(SimpleMatrix input);

    SimpleMatrix applyDerivativeOfActivationFunctionToMatrix(SimpleMatrix input);

    String getName();

}
