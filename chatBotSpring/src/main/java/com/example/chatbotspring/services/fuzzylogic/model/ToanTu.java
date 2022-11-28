package com.example.chatbotspring.services.fuzzylogic.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ToanTu {
    public double thap;
    public double binhThuong;
    public double cao;
    public List<Double> p = new ArrayList<>();

    public ToanTu() {
        thap = 0;
        binhThuong = 0;
        cao = 0;
    }

    public void set() {
        p.add(thap);
        p.add(binhThuong);
        p.add(cao);
    }

    @Override
    public String toString() {
        return "ToanTu{" + "thap=" + thap + ", binhthuong=" + binhThuong + ", cao=" + cao + '}';
    }
}
