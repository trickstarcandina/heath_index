package com.example.chatbotspring.services.fuzzylogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuLieu {
    public String gioiTinh;
    public int tuoi;
    public double chieuCaoMin;
    public double chieuCaoMax;
    public double canNangMin;
    public double canNangMax;
    public double duongHuyetMin;
    public double duongHuyetMax;
    public int nhipTimMin;
    public int nhipTimMax;
    public double cholesterolMin;
    public double cholesterolMax;


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
