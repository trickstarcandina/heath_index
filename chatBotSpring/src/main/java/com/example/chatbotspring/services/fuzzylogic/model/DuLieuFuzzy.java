package com.example.chatbotspring.services.fuzzylogic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.opencsv.bean.CsvBindByPosition;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DuLieuFuzzy {

    private String gioiTinh;
    private int tuoi;
    private double chieuCaoMin;
    private double chieuCaoMax;
    private double canNangMin;
    private double canNangMax;
    private double duongHuyetMin;
    private double duongHuyetMax;
    private int nhipTimMin;
    private int nhipTimMax;
    private double cholesterolMin;
    private double cholesterolMax;


    @Override
    public String toString() {
        return "DuLieu{" +
                "gioitinh='" + gioiTinh + '\'' +
                ", tuoi=" + tuoi +
                ", chieucaomin=" + chieuCaoMin +
                ", chieucaomax=" + chieuCaoMax +
                ", cannangmin=" + canNangMin +
                ", cannangmax=" + canNangMax +
                ", duonghuyetmin=" + duongHuyetMin +
                ", duonghuyetmax=" + duongHuyetMax +
                ", nhiptimmin=" + nhipTimMin +
                ", nhiptimmax=" + nhipTimMax +
                ", cholesteromin=" + cholesterolMin +
                ", cholesteromax=" + cholesterolMax +
                '}';
    }

}
