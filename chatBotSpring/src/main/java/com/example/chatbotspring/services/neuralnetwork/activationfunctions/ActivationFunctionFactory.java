package com.example.chatbotspring.services.neuralnetwork.activationfunctions;

import java.util.HashMap;
import java.util.Map;

public class ActivationFunctionFactory {

    private final Map<String, ActivationFunction> activationFunctionMap = new HashMap<>();

    public ActivationFunctionFactory () {
        ActivationFunction relu = new ReLuActivationFunction();
        activationFunctionMap.put(relu.getName(), relu);
    }

    public ActivationFunction getActivationFunctionByKey (String activationFunctionKey) {
        return activationFunctionMap.get(activationFunctionKey);
    }

    public void addActivationFunction(String key, ActivationFunction activationFunction) {
        activationFunctionMap.put(key, activationFunction);
    }

}
