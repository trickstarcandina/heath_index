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
    private String tuoi;
    @CsvBindByPosition(position = 2)
    private String chieuCao;
    @CsvBindByPosition(position = 3)
    private String canNang;
    @CsvBindByPosition(position = 4)
    private String duongHuyet;
    @CsvBindByPosition(position = 5)
    private String nhipTim;
    @CsvBindByPosition(position = 6)
    private String cholesterol;
    @CsvBindByPosition(position = 7)
    private String chiSoSucKhoe;

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
                ", chiSoSucKhoe=" + chiSoSucKhoe +
                '}';
    }
}
