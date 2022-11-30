package com.example.chatbotspring.services.fuzzylogic.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuLieuFuzzyConvert {
    @CsvBindByPosition(position = 0)
    private String gioiTinh;
    @CsvBindByPosition(position = 1)
    private String tuoi;
    @CsvBindByPosition(position = 2)
    private String chieuCaoMin;
    @CsvBindByPosition(position = 3)
    private String chieuCaoMax;
    @CsvBindByPosition(position = 4)
    private String canNangMin;
    @CsvBindByPosition(position = 5)
    private String canNangMax;
    @CsvBindByPosition(position = 6)
    private String duongHuyetMin;
    @CsvBindByPosition(position = 7)
    private String duongHuyetMax;
    @CsvBindByPosition(position = 8)
    private String nhipTimMin;
    @CsvBindByPosition(position = 9)
    private String nhipTimMax;
    @CsvBindByPosition(position = 10)
    private String cholesterolMin;
    @CsvBindByPosition(position = 11)
    private String cholesterolMax;

    @Override
    public String toString() {
        return "DuLieuFuzzyConvert {" +
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
