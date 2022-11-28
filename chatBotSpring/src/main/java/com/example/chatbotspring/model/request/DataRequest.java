package com.example.chatbotspring.model.request;

import com.example.chatbotspring.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {
    private Integer age;

    private Gender gender;

    private Double height;

    private Double weight;

    private Double bloodSugar;

    private Double heartBeat;

    private Double cholesterol;

}
