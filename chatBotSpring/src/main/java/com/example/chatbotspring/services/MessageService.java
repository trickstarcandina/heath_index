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
            7,
            1
    );

    private final List<DuLieuFuzzy> listDuLieuFuzzy = FileReaderCSV.readFileFuzzy("src/main/resources/fuzzy.csv");

    @Autowired
    public MessageService() {
        training();
    }

    public void training() {
        List<DuLieuTraining> duLieuTrainings = FileReaderCSV.readFileTraining("src/main/resources/training3.csv");
        logger.info("======START TRAINING======");
        duLieuTrainings.forEach(e -> {
            logger.info(e.toString());
            Fuzzy fuzzy = new Fuzzy();
            fuzzy.setListDuLieuFuzzy(listDuLieuFuzzy);
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
        Fuzzy fuzzy = new Fuzzy();
        fuzzy.setListDuLieuFuzzy(listDuLieuFuzzy);
        List<Double> listPredictFuzzy = fuzzy.ketQua(
                request.getGender().toString(),
                request.getAge(),
                request.getHeight(),
                request.getWeight(),
                request.getBloodSugar(),
                request.getHeartBeat(),
                request.getCholesterol()
        );
        double[] guess = new double[listPredictFuzzy.size()];
        for (int i = 0; i < listPredictFuzzy.size(); i++) {
            guess[i] = listPredictFuzzy.get(i);
        }
        double[] result = neuralNetwork.guess(guess);
        return new DataResponse(result[0]);
    }

}
