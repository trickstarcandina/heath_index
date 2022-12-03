package com.example.chatbotspring.model.request;

import com.example.chatbotspring.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {
    @NonNull
    private int age;

    @NonNull
    private Gender gender;

    @NonNull
    private double height;

    @NonNull
    private double weight;

    @NonNull
    @JsonProperty("blood_sugar")
    private double bloodSugar;

    @NonNull
    @JsonProperty("heart_beat")
    private double heartBeat;

    @NonNull
    private double cholesterol;

}
