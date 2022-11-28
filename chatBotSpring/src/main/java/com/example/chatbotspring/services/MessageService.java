package com.example.chatbotspring.services;

import com.example.chatbotspring.model.request.DataRequest;
import com.example.chatbotspring.model.response.DataResponse;
import com.example.chatbotspring.services.fuzzylogic.FuzzyService;
import com.example.chatbotspring.services.neuralnetwork.NeuralNetworkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MessageService {

    private final FuzzyService fuzzyService;

    private final NeuralNetworkService neuralNetworkService;

    public DataResponse predict(DataRequest request) {
        return new DataResponse();
    }

}
