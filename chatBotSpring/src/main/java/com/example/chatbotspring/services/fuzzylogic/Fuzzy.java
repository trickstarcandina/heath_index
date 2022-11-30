/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.chatbotspring.services.fuzzylogic;

import com.example.chatbotspring.services.fuzzylogic.model.DuLieuFuzzy;
import com.example.chatbotspring.services.fuzzylogic.model.Pair;
import com.example.chatbotspring.services.fuzzylogic.model.ToanTu;
import com.example.chatbotspring.utils.FileReaderCSV;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caotu
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fuzzy {
    private List<DuLieuFuzzy> listDuLieuFuzzy = new ArrayList<>(); // danh sach du lieu so sanh
    private ToanTu chieuCao = new ToanTu();
    private ToanTu canNang = new ToanTu();
    private ToanTu duongHuyet = new ToanTu();
    private ToanTu nhipTim = new ToanTu();
    private ToanTu cholesterol = new ToanTu();
    // cac truong hop
    private List<Pair> listSdd2 = new ArrayList<>(); // cac cap truong hop xay ra(chieu cao, can nang, duong huyet)
    private List<Pair> listSdd1 = new ArrayList<>();
    private List<Pair> listBthuong = new ArrayList<>();
    private List<Pair> listThuaCan = new ArrayList<>();
    private List<Pair> listBeoPhi = new ArrayList<>();
    private List<Pair> listTuanHoanKem = new ArrayList<>(); // nhip tim, cholesterol, duong huyet
    private List<Pair> listTuanHoanBthg = new ArrayList<>();
    private List<Pair> listTuanHoanTot = new ArrayList<>();
    // gia tri cac truong hop
    private Double pSdd2;
    private Double pSdd1;
    private Double pBthg;
    private Double pThuaCan;
    private Double pBeoPhi;
    private Double pTuanHoanKem;
    private Double pTuanHoanBthg;
    private Double pTuanHoanTot;

    // doc du lieu tu file csv
//    public Fuzzy() { // doc du lieu tu file csv
//        String excelFilePath = "src/main/resources/fuzzy.csv";
//        listDuLieuFuzzy = FileReaderCSV.readFileFuzzy(excelFilePath);
//    }

    public static void main(String[] args) throws IOException {
        Fuzzy fuzzy = new Fuzzy();
        List<Double> list = fuzzy.ketQua("nu", 4, 100, 20, 6, 113, 224);
        System.out.println(fuzzy.timkiem("nu", 4).toString());
        System.out.println(fuzzy.chieuCao.toString());
        System.out.println(fuzzy.canNang.toString());
        System.out.println(fuzzy.duongHuyet.toString());
        System.out.println(fuzzy.nhipTim.toString());
        System.out.println(fuzzy.cholesterol.toString());
        for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.println(fuzzy.list.size());

    }

    // tim kiem du lieu chuan theo gioi tinh va tuoi
    public DuLieuFuzzy timkiem(String gioitinh, int tuoi) {
        for (DuLieuFuzzy duLieuFuzzy : listDuLieuFuzzy)
            if (duLieuFuzzy.getGioiTinh().equals(gioitinh) && duLieuFuzzy.getTuoi() == tuoi) return duLieuFuzzy;
        return null;
    }

    // kiem tra truong hop thoa man
    public int truongHopThoaManDinhDuong(int x, int y, int z) {
        for (Pair pair : listSdd2) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 1;
        for (Pair pair : listSdd1) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 2;
        for (Pair pair : listBthuong) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 3;
        for (Pair pair : listThuaCan) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 4;
        for (Pair pair : listBeoPhi) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 5;
        return 0;
    }

    // kiem tra truong hop thoa man
    public int truongHopThoaManTuanHoan(int x, int y, int z) {
        for (Pair pair : listTuanHoanKem) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 1;
        for (Pair pair : listTuanHoanBthg) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 2;
        for (Pair pair : listTuanHoanTot) if (x == pair.getX() && y == pair.getY() && z == pair.getZ()) return 3;
        return 0;
    }


    // ham and cua 3 toan tu dinh duong
    public double kethopDinhDuong(int x, int y, int z) {
        return Double.min(duongHuyet.getP().get(x), Double.min(chieuCao.getP().get(y), canNang.getP().get(z)));
    }

    // ham and cua 3 toan tu tuan hoan
    public double ketHopTuanHoan(int x, int y, int z) {
        return Double.min(duongHuyet.getP().get(x), Double.min(cholesterol.getP().get(y), nhipTim.getP().get(z)));
    }

    // gia tri tap mo, thanh vien tap mo
    public List<Double> ketQua(String gioitinh, int tuoi, double chieuCao, double canNang,
                               double duongHuyet, double nhipTim, double cholesterol) {
        init(gioitinh, tuoi, chieuCao, canNang, duongHuyet, nhipTim, cholesterol);

        pSdd2 = 0.0;
        pSdd1 = 0.0;
        pBthg = 0.0;
        pThuaCan = 0.0;
        pBeoPhi = 0.0;
        pTuanHoanKem = 0.0;
        pTuanHoanBthg = 0.0;
        pTuanHoanTot = 0.0;

        for (int x = 0; x <= 2; x++)
            for (int y = 0; y <= 2; y++)
                for (int z = 0; z <= 2; z++)
                    switch (truongHopThoaManDinhDuong(x, y, z)) {
                        case 1:
                            pSdd2 = Double.max(pSdd2, kethopDinhDuong(x, y, z));
                            break;
                        case 2:
                            pSdd1 = Double.max(pSdd1, kethopDinhDuong(x, y, z));
                            break;
                        case 3:
                            pBthg = Double.max(pBthg, kethopDinhDuong(x, y, z));
                            break;
                        case 4:
                            pThuaCan = Double.max(pThuaCan, kethopDinhDuong(x, y, z));
                            break;
                        case 5:
                            pBeoPhi = Double.max(pBeoPhi, kethopDinhDuong(x, y, z));
                            break;
                        default:
                            break;
                    }

        for (int x = 0; x <= 2; x++)
            for (int y = 0; y <= 2; y++)
                for (int z = 0; z <= 2; z++)
                    switch (truongHopThoaManTuanHoan(x, y, z)) {
                        case 1:
                            pTuanHoanKem = Double.max(pTuanHoanKem, ketHopTuanHoan(x, y, z));
                            break;
                        case 2:
                            pTuanHoanBthg = Double.max(pTuanHoanBthg, ketHopTuanHoan(x, y, z));
                            break;
                        case 3:
                            pTuanHoanTot = Double.max(pTuanHoanTot, ketHopTuanHoan(x, y, z));
                            break;
                        default:
                            break;
                    }
        List<Double> res = new ArrayList<>();
        res.add(pSdd2);
        res.add(pSdd1);
        res.add(pBthg);
        res.add(pThuaCan);
        res.add(pBeoPhi);
        res.add(pTuanHoanKem);
        res.add(pTuanHoanBthg);
        res.add(pTuanHoanTot);
        return res;
    }

    // tinh xac suat 3 toan tu dau vao, liet ke cac truong hop
    public void init(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double choles) {
        DuLieuFuzzy dl = timkiem(gioitinh, tuoi);
        // tinh gia tri mo chieu cao
        if (cc < dl.getChieuCaoMin()) {
            chieuCao.setThap(1);
        } else if (cc >= dl.getChieuCaoMin() && cc < (dl.getChieuCaoMin() + dl.getChieuCaoMax()) / 2) {
            chieuCao.setBinhThuong(2 * (cc - dl.getChieuCaoMin()) / (dl.getChieuCaoMax() - dl.getChieuCaoMin()));
            chieuCao.setThap(1 - chieuCao.getBinhThuong());
        } else if (cc >= (dl.getChieuCaoMin() + dl.getChieuCaoMax()) / 2 && cc < dl.getChieuCaoMax()) {
            chieuCao.setBinhThuong(2 * (dl.getChieuCaoMax() - cc) / (dl.getChieuCaoMax() - dl.getChieuCaoMin()));
            chieuCao.setCao(1 - chieuCao.getBinhThuong());
        } else if (cc >= dl.getChieuCaoMax()) chieuCao.setCao(1);
        chieuCao.set();
        // tinh gia tri mo can nang
        if (cn < dl.getCanNangMin()) {
            canNang.setThap(1);
        } else if (cn >= dl.getCanNangMin() && cn < (dl.getCanNangMin() + dl.getCanNangMax()) / 2) {
            canNang.setBinhThuong(2 * (cn - dl.getCanNangMin()) / (dl.getCanNangMax() - dl.getCanNangMin()));
            canNang.setThap(1 - canNang.getBinhThuong());
        } else if (cn >= (dl.getCanNangMin() + dl.getCanNangMax()) / 2 && cn < dl.getCanNangMax()) {
            canNang.setBinhThuong(2 * (dl.getCanNangMax() - cn) / (dl.getCanNangMax() - dl.getCanNangMin()));
            canNang.setCao(1 - canNang.getBinhThuong());
        } else if (cn >= dl.getCanNangMax()) canNang.setCao(1);
        canNang.set();
        // tinh gia tri mo duong huyet
        if (dh < dl.getDuongHuyetMin()) {
            duongHuyet.setThap(1);
        } else if (dh >= dl.getDuongHuyetMin() && dh < (dl.getDuongHuyetMin() + dl.getDuongHuyetMax()) / 2) {
            duongHuyet.setBinhThuong(2 * (dh - dl.getDuongHuyetMin()) / (dl.getDuongHuyetMax() - dl.getDuongHuyetMin()));
            duongHuyet.setThap(1 - duongHuyet.getBinhThuong());
        } else if (dh >= (dl.getDuongHuyetMin() + dl.getDuongHuyetMax()) / 2 && dh < dl.getDuongHuyetMax()) {
            duongHuyet.setBinhThuong(2 * (dl.getDuongHuyetMax() - dh) / (dl.getDuongHuyetMax() - dl.getDuongHuyetMin()));
            duongHuyet.setCao(1 - duongHuyet.getBinhThuong());
        } else if (dh >= dl.getDuongHuyetMax()) {
            duongHuyet.setCao(1);
        }
        duongHuyet.set();
        // tinh gia tri mo nhip tim
        if (nt < dl.getNhipTimMin()) {
            nhipTim.setThap(1);
        } else if (nt >= dl.getNhipTimMin() && nt < (dl.getNhipTimMin() + dl.getNhipTimMax()) / 2) {
            nhipTim.setBinhThuong(2 * (nt - dl.getNhipTimMin()) / (dl.getNhipTimMax() - dl.getNhipTimMin()));
            nhipTim.setThap(1 - nhipTim.getBinhThuong());
        } else if (nt >= (dl.getNhipTimMin() + dl.getNhipTimMax()) / 2 && dh < dl.getNhipTimMax()) {
            nhipTim.setBinhThuong(2 * (dl.getNhipTimMax() - nt) / (dl.getNhipTimMax() - dl.getNhipTimMin()));
            nhipTim.setCao(1 - nhipTim.getBinhThuong());
        } else if (nt >= dl.getNhipTimMax()) {
            nhipTim.setCao(1);
        }
        nhipTim.set();
        // tinh gia tri mo cholesterol
        if (choles < dl.getCholesterolMin()) {
            cholesterol.setThap(1);
        } else if (choles >= dl.getCholesterolMin() && choles < (dl.getCholesterolMin() + dl.getCholesterolMax()) / 2) {
            cholesterol.setBinhThuong(2 * (choles - dl.getCholesterolMin()) / (dl.getCholesterolMax() - dl.getCholesterolMin()));
            cholesterol.setThap(1 - cholesterol.getBinhThuong());
        } else if (choles >= (dl.getCholesterolMin() + dl.getCholesterolMax()) / 2 && choles < dl.getCholesterolMax()) {
            cholesterol.setBinhThuong(2 * (dl.getCholesterolMax() - choles) / (dl.getCholesterolMax() - dl.getCholesterolMin()));
            cholesterol.setCao(1 - cholesterol.getBinhThuong());
        } else if (choles >= dl.getCholesterolMax()) {
            cholesterol.setCao(1);
        }
        cholesterol.set();
        // thap = 0, bt = 1, cao = 2, duong huyet, chieu cao, can nang
        listSdd2.add(new Pair(1, 0, 0));
        listSdd2.add(new Pair(0, 0, 1));
        listSdd2.add(new Pair(0, 1, 0));
        listSdd2.add(new Pair(0, 2, 0));
        listSdd2.add(new Pair(0, 0, 0));
        listSdd1.add(new Pair(1, 1, 0));
        listSdd1.add(new Pair(1, 2, 0));
        listSdd1.add(new Pair(1, 0, 1));
        listSdd1.add(new Pair(0, 1, 1));
        listSdd1.add(new Pair(0, 2, 1));
        listSdd1.add(new Pair(2, 0, 0));
        listBthuong.add(new Pair(1, 1, 1));
        listBthuong.add(new Pair(1, 2, 1));
        listBthuong.add(new Pair(0, 1, 2));
        listBthuong.add(new Pair(0, 2, 2));
        listBthuong.add(new Pair(2, 0, 1));
        listBthuong.add(new Pair(2, 1, 0));
        listBthuong.add(new Pair(2, 2, 0));
        listThuaCan.add(new Pair(1, 1, 2));
        listThuaCan.add(new Pair(1, 2, 2));
        listThuaCan.add(new Pair(0, 0, 2));
        listThuaCan.add(new Pair(2, 1, 1));
        listThuaCan.add(new Pair(2, 2, 1));
        listBeoPhi.add(new Pair(1, 0, 2));
        listBeoPhi.add(new Pair(2, 0, 2));
        listBeoPhi.add(new Pair(2, 1, 2));
        listBeoPhi.add(new Pair(2, 2, 2));
        // thap = 0, bt = 1, cao = 2, duonghuyet, choles, nhip tim

        listTuanHoanKem.add(new Pair(1, 0, 0));
        listTuanHoanKem.add(new Pair(1, 1, 0));
        listTuanHoanKem.add(new Pair(1, 0, 1));
        listTuanHoanKem.add(new Pair(0, 0, 0));
        listTuanHoanKem.add(new Pair(0, 2, 0));
        listTuanHoanKem.add(new Pair(0, 1, 0));
        listTuanHoanKem.add(new Pair(0, 0, 1));
        listTuanHoanKem.add(new Pair(0, 1, 1));
        listTuanHoanKem.add(new Pair(0, 0, 2));
        listTuanHoanKem.add(new Pair(2, 0, 0));

        listTuanHoanBthg.add(new Pair(1, 2, 0));
        listTuanHoanBthg.add(new Pair(1, 1, 1));
        listTuanHoanBthg.add(new Pair(1, 0, 2));
        listTuanHoanBthg.add(new Pair(0, 2, 1));
        listTuanHoanBthg.add(new Pair(0, 1, 2));
        listTuanHoanBthg.add(new Pair(2, 1, 0));
        listTuanHoanBthg.add(new Pair(2, 0, 1));
        listTuanHoanBthg.add(new Pair(2, 0, 2));
        listTuanHoanBthg.add(new Pair(2, 1, 2));

        listTuanHoanTot.add(new Pair(1, 2, 1));
        listTuanHoanTot.add(new Pair(1, 2, 2));
        listTuanHoanTot.add(new Pair(1, 1, 2));
        listTuanHoanTot.add(new Pair(0, 2, 2));
        listTuanHoanTot.add(new Pair(2, 2, 0));
        listTuanHoanTot.add(new Pair(2, 1, 1));
        listTuanHoanTot.add(new Pair(2, 2, 1));
        listTuanHoanTot.add(new Pair(2, 2, 2));
    }
}
// fuzzy.ketqua("nam", 1, 80, 10, 6); 0.0 0.5333328 0.46666718 0.22727275 0.0
// fuzzy.ketqua("nam", 1, 90, 15, 12); 0.0 0.0 0.0 0.0 1.0
// fuzzy.ketqua("nam", 1, 80, 9, 4); 0.6904764 0.13333385 0.0 0.0 0.0
// fuzzy.ketqua("nu", 4, 100, 20, 6); 0.31395352 0.32608697 0.673913 0.31395352 0.22727275