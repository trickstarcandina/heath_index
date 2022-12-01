package com.example.chatbotspring.services.neuralnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DuLieuTraining {

    private String gioiTinh;
    private int tuoi;
    private double chieuCao;
    private double canNang;
    private double duongHuyet;
    private int nhipTim;
    private double cholesterol;
    private double chiSoSucKhoe;

    @Override
    public String toString() {
        return "DuLieuTraining{" +
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
