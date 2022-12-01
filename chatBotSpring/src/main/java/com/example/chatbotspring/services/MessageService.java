package com.example.chatbotspring.services;

import com.example.chatbotspring.model.request.DataRequest;
import com.example.chatbotspring.model.response.DataResponse;
import com.example.chatbotspring.services.fuzzylogic.Fuzzy;
import com.example.chatbotspring.services.fuzzylogic.model.DuLieuFuzzy;
import com.example.chatbotspring.services.neuralnetwork.NeuralNetwork;
import com.example.chatbotspring.services.neuralnetwork.model.DuLieuTraining;
import com.example.chatbotspring.utils.FileReaderCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final NeuralNetwork neuralNetwork = new NeuralNetwork(
            8,
            5,
            11,
            1
    );

    private final Fuzzy fuzzy = new Fuzzy();

    @Autowired
    public MessageService() {
        training();
    }

    public void training() {
        List<DuLieuTraining> duLieuTrainings = FileReaderCSV.readFileTraining("src/main/resources/training.csv");
        logger.info("======START TRAINING======");
        duLieuTrainings.forEach(e -> {
            logger.info(e.toString());
            List<Double> listResultFuzzy = fuzzy.ketQua(
                    e.getGioiTinh(),
                    e.getTuoi(),
                    e.getChieuCao(),
                    e.getCanNang(),
                    e.getDuongHuyet(),
                    e.getNhipTim(),
                    e.getCholesterol()
            );
            double[] inputArray = new double[listResultFuzzy.size()];
            for (int i = 0; i < listResultFuzzy.size(); i++) {
                inputArray[i] = listResultFuzzy.get(i);
            }
            double[] targetArray = new double[1];
            targetArray[0] = e.getChiSoSucKhoe();
            neuralNetwork.train(inputArray, targetArray);
        });
        logger.info("======END TRAINING======");
    }

    public DataResponse predict(DataRequest request) {
        double[] guess = new double[8];
        guess[0] = 0.5;
        guess[1] = 0.9;
        guess[2] = 0.3;
        guess[3] = 0.3;
        guess[4] = 0.3;
        guess[5] = 0.6;
        guess[6] = 0.3;
        guess[7] = 0.1;
        double[] result = neuralNetwork.guess(guess);
        return new DataResponse(result[0]);
    }

}
