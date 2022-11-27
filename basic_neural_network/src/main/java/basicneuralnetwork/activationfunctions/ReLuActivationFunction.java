package basicneuralnetwork.activationfunctions;

import org.ejml.simple.SimpleMatrix;

/**
 * Created by KimFeichtinger on 26.04.18.
 */
public class ReLuActivationFunction implements ActivationFunction {

    private static final String NAME = "RELU";

    public SimpleMatrix applyActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
        for (int i = 0; i < input.numRows(); i++) {
            // số cột ma trận luôn là 0 do ta đang xét một input là một cột của ma trận
            double value = input.get(i, 0);
            double result = value > 0 ? value : 0;
            output.set(i, 0, result);
        }
        return output;
    }

    public SimpleMatrix applyDerivativeOfActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
        for (int i = 0; i < input.numRows(); i++) {
            double value = input.get(i, 0);
            double result = value > 0 ? 1 : 0;
            output.set(i, 0, result);
        }
        // input > 0: 1, else 0
        return output;
    }

    public String getName() {
        return NAME;
    }
}
