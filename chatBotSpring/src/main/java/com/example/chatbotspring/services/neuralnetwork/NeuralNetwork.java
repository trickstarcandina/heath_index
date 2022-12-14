package com.example.chatbotspring.services.neuralnetwork;

import com.example.chatbotspring.services.neuralnetwork.activationfunctions.ActivationFunction;
import com.example.chatbotspring.services.neuralnetwork.activationfunctions.ActivationFunctionFactory;
import com.example.chatbotspring.utils.MatrixUtilities;
import com.example.chatbotspring.utils.WrongDimensionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ejml.simple.SimpleMatrix;

import java.util.Random;

@Data
@AllArgsConstructor
public class NeuralNetwork {

    private final ActivationFunctionFactory activationFunctionFactory = new ActivationFunctionFactory();

    private final Random random = new Random();

    private final int inputNodes;
    private final int hiddenLayers;
    private final int hiddenNodes;
    private final int outputNodes;

    private SimpleMatrix[] weights;
    private SimpleMatrix[] biases;

    private double learningRate;

    private String activationFunctionKey;

    // constructor
    public NeuralNetwork(int inputNodes, int hiddenLayers,
                         int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenLayers = hiddenLayers;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        initializeDefaultValues();
        initializeWeights();
        initializeBiases();
    }

    public NeuralNetwork(NeuralNetwork nn) {
        this.inputNodes = nn.inputNodes;
        this.hiddenLayers = nn.hiddenLayers;
        this.hiddenNodes = nn.hiddenNodes;
        this.outputNodes = nn.outputNodes;

        this.weights = new SimpleMatrix[hiddenLayers + 1];
        this.biases = new SimpleMatrix[hiddenLayers + 1];

        for (int i = 0; i < nn.weights.length; i++) {
            this.weights[i] = nn.weights[i].copy();
        }

        for (int i = 0; i < nn.biases.length; i++) {
            this.biases[i] = nn.biases[i].copy();
        }

        this.learningRate = nn.learningRate;

        this.activationFunctionKey = nn.activationFunctionKey;
    }

    private void initializeDefaultValues() {
        this.setLearningRate(0.05);
        this.setActivationFunction(ActivationFunction.RELU);
    }

    private void initializeWeights() {
        weights = new SimpleMatrix[hiddenLayers + 1]; // k??ch th?????c s??? l???p ???n + 1
        // kh???i t???o tr???ng s??? ng???u nhi??n
        for (int i = 0; i < weights.length; i++) {
            if (i == 0) { // l???p tr???ng s??? ?????u ti??n k???t n???i input v???i l???p ???n
                weights[i] = SimpleMatrix.random64(hiddenNodes, inputNodes,
                        -1, 1, random);
            } else if (i == weights.length - 1) { // l???p tr???ng s??? ?????u ti??n k???t n???i l???p ???n v???i output
                weights[i] = SimpleMatrix.random64(outputNodes, hiddenNodes,
                        -1, 1, random);
            } else { // everything else
                weights[i] = SimpleMatrix.random64(hiddenNodes, hiddenNodes,
                        -1, 1, random);
            }
        }
    }

    private void initializeBiases() {
        biases = new SimpleMatrix[hiddenLayers + 1];
        for (int i = 0; i < biases.length; i++) {
            if (i == biases.length - 1) { // bias l???p output
                biases[i] = SimpleMatrix.random64(outputNodes, 1,
                        -1, 1, random);
            } else {
                biases[i] = SimpleMatrix.random64(hiddenNodes, 1,
                        -1, 1, random);
            }
        }
    }

    public double[] guess(double[] input) {
        if (input.length != inputNodes){
            throw new WrongDimensionException(input.length, inputNodes, "Input");
        } else {
            ActivationFunction activationFunction =
                    activationFunctionFactory.getActivationFunctionByKey(activationFunctionKey);
            SimpleMatrix output = MatrixUtilities.arrayToMatrix(input);
            for (int i = 0; i < hiddenLayers + 1; i++) {
                output = calculateLayer(weights[i], biases[i], output, activationFunction);
            }
            return MatrixUtilities.getColumnFromMatrixAsArray(output, 0);
        }
    }

    public void train(double[] inputArray, double[] targetArray) {
        if (inputArray.length != inputNodes) {
            throw new WrongDimensionException(inputArray.length, inputNodes, "Input");
        } else if (targetArray.length != outputNodes) {
            throw new WrongDimensionException(targetArray.length, outputNodes, "Output");
        } else {
            // Get ActivationFunction-object from the map by key
            ActivationFunction activationFunction = activationFunctionFactory.getActivationFunctionByKey(activationFunctionKey);

            // Transform 2D array to matrix
            SimpleMatrix input = MatrixUtilities.arrayToMatrix(inputArray);
            SimpleMatrix target = MatrixUtilities.arrayToMatrix(targetArray);

            // t??nh to??n gi?? tr??? cho t???ng l???p layer
            SimpleMatrix layers[] = new SimpleMatrix[hiddenLayers + 2];
            layers[0] = input;
            for (int j = 1; j < hiddenLayers + 2; j++) {
                layers[j] = calculateLayer(weights[j - 1], biases[j - 1],
                        input, activationFunction);
                input = layers[j];
            }
            // tinh toan nguoc
            for (int n = hiddenLayers + 1; n > 0; n--) {
                // t??nh to??n sai s???
                SimpleMatrix errors = target.minus(layers[n]);
                // t??nh gradient
                SimpleMatrix gradientNoScale = calculateGradient(layers[n], errors,
                        activationFunction);

                SimpleMatrix gradients = gradientNoScale.scale(learningRate);
                // t??nh delta
                SimpleMatrix deltas = calculateDeltas(gradients, layers[n - 1]);
                // ??p d???ng gradient v??o bias
                biases[n - 1] = biases[n - 1].plus(gradients);

                // t??nh to??n v?? ?????t l???i target cho l???p tr?????c
                // w = w * errors ? w = w * dao ham tai n
                SimpleMatrix previousError = weights[n - 1].transpose()
//                        .mult(errors);
                        .mult(gradientNoScale);

                // ??p d???ng delta v??o weights
                weights[n - 1] = weights[n - 1].plus(deltas);

                target = previousError.plus(layers[n - 1]);
            }
        }
    }

    private SimpleMatrix calculateLayer(SimpleMatrix weights, SimpleMatrix bias,
                                        SimpleMatrix input, ActivationFunction activationFunction) {
        SimpleMatrix result = weights.mult(input); // weight * input
        result = result.plus(bias); // (weight * input) + bias
        return applyActivationFunction(result, false, activationFunction);
    }

    private SimpleMatrix calculateGradient(SimpleMatrix layer, SimpleMatrix error,
                                           ActivationFunction activationFunction) {
        SimpleMatrix gradient = applyActivationFunction(layer, true, activationFunction);
        gradient = gradient.elementMult(error); // ?????o h??m activation func * (target - calc_output)
        return gradient;
                //.scale(learningRate); // gradient * learning_date
    }

    private SimpleMatrix calculateDeltas(SimpleMatrix gradient,
                                         SimpleMatrix layer) {
        return gradient.mult(layer.transpose()).divide(1000.0);
    }

    private SimpleMatrix applyActivationFunction(SimpleMatrix input, boolean derivative, ActivationFunction activationFunction) {
        // Applies either derivative of activation function or regular activation function to a matrix and returns the result
        return derivative ? activationFunction.applyDerivativeOfActivationFunctionToMatrix(input)
                : activationFunction.applyActivationFunctionToMatrix(input);
    }

    public void setActivationFunction(String activationFunction) {
        this.activationFunctionKey = activationFunction;
    }

}
