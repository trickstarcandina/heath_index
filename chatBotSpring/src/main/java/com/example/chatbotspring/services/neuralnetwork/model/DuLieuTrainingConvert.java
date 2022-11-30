package com.example.chatbotspring.services.neuralnetwork.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuLieuTrainingConvert {

    @CsvBindByPosition(position = 0)
    private String gioiTinh;
    @CsvBindByPosition(position = 1)
    private int tuoi;
    @CsvBindByPosition(position = 2)
    private double chieuCao;
    @CsvBindByPosition(position = 3)
    private double canNang;
    @CsvBindByPosition(position = 4)
    private double duongHuyet;
    @CsvBindByPosition(position = 5)
    private int nhipTim;
    @CsvBindByPosition(position = 6)
    private double cholesterol;

    @Override
    public String toString() {
        return "DuLieuTrainingConvert{" +
                "gioiTinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", chieuCao=" + chieuCao +
                ", canNang=" + canNang +
                ", duongHuyet=" + duongHuyet +
                ", nhipTim=" + nhipTim +
                ", cholesterol=" + cholesterol +
                '}';
    }
}
