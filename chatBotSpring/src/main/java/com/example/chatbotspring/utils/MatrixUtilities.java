package com.example.chatbotspring.utils;

import org.ejml.simple.SimpleMatrix;

import java.util.Random;

public class MatrixUtilities {

    //convert 2D array to SimpleMatrix
    public static SimpleMatrix arrayToMatrix(double[] i) {
        double[][] input = {i};
        return new SimpleMatrix(input).transpose();
    }

    public static double[] getColumnFromMatrixAsArray(SimpleMatrix data, int column) {
        double[] result = new double[data.numRows()];
        for (int i = 0; i < result.length; i++) {
            result[i] = data.get(i, column);
        }
        return result;
    }

    // Merge two matrices and return a new one
    public static SimpleMatrix mergeMatrices(SimpleMatrix matrixA, SimpleMatrix matrixB, double probability) {
        if (matrixA.numCols() != matrixB.numCols() || matrixA.numRows() != matrixB.numRows()) {
            throw new WrongDimensionException();
        } else {
            Random random = new Random();
            SimpleMatrix result = new SimpleMatrix(matrixA.numRows(), matrixA.numCols());

            for (int i = 0; i < matrixA.getNumElements(); i++) {
                // %-chance of replacing this value with the one from the input nn
                if (random.nextDouble() > probability) {
                    result.set(i, matrixA.get(i));
                } else {
                    result.set(i, matrixB.get(i));
                }
            }

            return result;
        }
    }

}
