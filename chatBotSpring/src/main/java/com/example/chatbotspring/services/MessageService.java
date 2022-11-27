package com.example.chatbotspring.services;

import com.example.chatbotspring.model.request.DataRequest;
import com.example.chatbotspring.model.response.DataResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MessageService {

    public DataResponse predict(DataRequest request) {
        return new DataResponse();
    }

}
