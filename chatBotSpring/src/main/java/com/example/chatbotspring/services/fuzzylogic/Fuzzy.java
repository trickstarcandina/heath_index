/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.chatbotspring.services.fuzzylogic;

import com.example.chatbotspring.services.fuzzylogic.model.DuLieu;
import com.example.chatbotspring.services.fuzzylogic.model.Pair;
import com.example.chatbotspring.services.fuzzylogic.model.ToanTu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caotu
 */
public class Fuzzy {
    public List<DuLieu> list = new ArrayList<DuLieu>(); // danh sach du lieu so sanh
    public ToanTu chieuCao = new ToanTu();
    public ToanTu canNang = new ToanTu();
    public ToanTu duongHuyet = new ToanTu();
    public ToanTu nhiptim = new ToanTu();
    public ToanTu cholesterol = new ToanTu();
    // cac truong hop
    public List<Pair> listsdd2 = new ArrayList<>(); // cac cap truong hop xay ra(chieu cao, can nang, duong huyet)
    public List<Pair> listsdd1 = new ArrayList<>();
    public List<Pair> listbt = new ArrayList<>();
    public List<Pair> listtc = new ArrayList<>();
    public List<Pair> listbp = new ArrayList<>();
    public List<Pair> listtuanhoan_k = new ArrayList<>(); // nhip tim, cholesterol, duong huyet
    public List<Pair> listtuanhoan_bt = new ArrayList<>();
    public List<Pair> listtuanhoan_t = new ArrayList<>();
    // gia tri cac truong hop
    public Double p_sdd2;
    public Double p_sdd1;
    public Double p_bt;
    public Double p_tc;
    public Double p_bp;
    public Double th_k;
    public Double th_bt;
    public Double th_t;

    // doc du lieu tu file csv
    public Fuzzy() throws IOException { // doc du lieu tu file csv
        String excelFilePath = "E:\\CODE\\Intellij\\Fuzzy\\src\\main\\java\\fuzzy\\input.xlsx";
        list = InputController.read(excelFilePath);
    }

    public static void main(String[] args) throws IOException {
        Fuzzy fuzzy = new Fuzzy();
        List<Double> list = fuzzy.ketQua("nu", 4, 100, 20, 6, 113, 224);
        System.out.println(fuzzy.timkiem("nu", 4).toString());
        System.out.println(fuzzy.chieuCao.toString());
        System.out.println(fuzzy.canNang.toString());
        System.out.println(fuzzy.duongHuyet.toString());
        System.out.println(fuzzy.nhiptim.toString());
        System.out.println(fuzzy.cholesterol.toString());
        for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.println(fuzzy.list.size());

    }

    // tim kiem du lieu chuan theo gioi tinh va tuoi
    public DuLieu timkiem(String gioitinh, int tuoi) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).gioiTinh.equals(gioitinh) && list.get(i).getTuoi() == tuoi) return list.get(i);
        return null;
    }

    // kiem tra truong hop thoa man
    public int truongHopThoaManDinhDuong(int x, int y, int z) {
        for (Pair pair : listsdd2) if (x == pair.x && y == pair.y && z == pair.z) return 1;
        for (Pair pair : listsdd1) if (x == pair.x && y == pair.y && z == pair.z) return 2;
        for (Pair pair : listbt) if (x == pair.x && y == pair.y && z == pair.z) return 3;
        for (Pair pair : listtc) if (x == pair.x && y == pair.y && z == pair.z) return 4;
        for (Pair pair : listbp) if (x == pair.x && y == pair.y && z == pair.z) return 5;
        return 0;
    }

    // kiem tra truong hop thoa man
    public int truongHopThoaManTuanHoan(int x, int y, int z) {
        for (Pair pair : listtuanhoan_k) if (x == pair.x && y == pair.y && z == pair.z) return 1;
        for (Pair pair : listtuanhoan_bt) if (x == pair.x && y == pair.y && z == pair.z) return 2;
        for (Pair pair : listtuanhoan_t) if (x == pair.x && y == pair.y && z == pair.z) return 3;
        return 0;
    }


    // ham and cua 3 toan tu dinh duong
    public double kethopDinhDuong(int x, int y, int z) {
        return Double.min(duongHuyet.p.get(x), Double.min(chieuCao.p.get(y), canNang.p.get(z)));
    }

    // ham and cua 3 toan tu tuan hoan
    public double ketHopTuanHoan(int x, int y, int z) {
        return Double.min(duongHuyet.p.get(x), Double.min(cholesterol.p.get(y), nhiptim.p.get(z)));
    }

    // gia tri tap mo, thanh vien tap mo
    public List<Double> ketQua(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double cholesterol) {
        init(gioitinh, tuoi, cc, cn, dh, nt, cholesterol);

        p_sdd2 = 0.0;
        p_sdd1 = 0.0;
        p_bt = 0.0;
        p_tc = 0.0;
        p_bp = 0.0;
        th_k = 0.0;
        th_bt = 0.0;
        th_t = 0.0;

        for (int x = 0; x <= 2; x++)
            for (int y = 0; y <= 2; y++)
                for (int z = 0; z <= 2; z++)
                    switch (truongHopThoaManDinhDuong(x, y, z)) {
                        case 1:
                            p_sdd2 = Double.max(p_sdd2, kethopDinhDuong(x, y, z));
                            break;
                        case 2:
                            p_sdd1 = Double.max(p_sdd1, kethopDinhDuong(x, y, z));
                            break;
                        case 3:
                            p_bt = Double.max(p_bt, kethopDinhDuong(x, y, z));
                            break;
                        case 4:
                            p_tc = Double.max(p_tc, kethopDinhDuong(x, y, z));
                            break;
                        case 5:
                            p_bp = Double.max(p_bp, kethopDinhDuong(x, y, z));
                            break;
                        default:
                            break;
                    }

        for (int x = 0; x <= 2; x++)
            for (int y = 0; y <= 2; y++)
                for (int z = 0; z <= 2; z++)
                    switch (truongHopThoaManTuanHoan(x, y, z)) {
                        case 1:
                            th_k = Double.max(th_k, ketHopTuanHoan(x, y, z));
                            break;
                        case 2:
                            th_bt = Double.max(th_bt, ketHopTuanHoan(x, y, z));
                            break;
                        case 3:
                            th_t = Double.max(th_t, ketHopTuanHoan(x, y, z));
                            break;
                        default:
                            break;
                    }
        List<Double> res = new ArrayList<>();
        res.add(p_sdd2);
        res.add(p_sdd1);
        res.add(p_bt);
        res.add(p_tc);
        res.add(p_bp);
        res.add(th_k);
        res.add(th_bt);
        res.add(th_t);
        return res;
    }

    // tinh xac suat 3 toan tu dau vao, liet ke cac truong hop
    public void init(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double choles) {
        DuLieu dl = timkiem(gioitinh, tuoi);
        // tinh gia tri mo chieu cao
        if (cc < dl.chieuCaoMin) {
            chieuCao.thap = 1;
        } else if (cc >= dl.chieuCaoMin && cc < (dl.chieuCaoMin + dl.chieuCaoMax) / 2) {
            chieuCao.binhThuong = 2 * (cc - dl.chieuCaoMin) / (dl.chieuCaoMax - dl.chieuCaoMin);
            chieuCao.thap = 1 - chieuCao.binhThuong;
        } else if (cc >= (dl.chieuCaoMin + dl.chieuCaoMax) / 2 && cc < dl.chieuCaoMax) {
            chieuCao.binhThuong = 2 * (dl.chieuCaoMax - cc) / (dl.chieuCaoMax - dl.chieuCaoMin);
            chieuCao.cao = 1 - chieuCao.binhThuong;
        } else if (cc >= dl.chieuCaoMax) chieuCao.cao = 1;
        chieuCao.set();
        // tinh gia tri mo can nang
        if (cn < dl.canNangMin) {
            canNang.thap = 1;
        } else if (cn >= dl.canNangMin && cn < (dl.canNangMin + dl.canNangMax) / 2) {
            canNang.binhThuong = 2 * (cn - dl.canNangMin) / (dl.canNangMax - dl.canNangMin);
            canNang.thap = 1 - canNang.binhThuong;
        } else if (cn >= (dl.canNangMin + dl.canNangMax) / 2 && cn < dl.canNangMax) {
            canNang.binhThuong = 2 * (dl.canNangMax - cn) / (dl.canNangMax - dl.canNangMin);
            canNang.cao = 1 - canNang.binhThuong;
        } else if (cn >= dl.canNangMax) canNang.cao = 1;
        canNang.set();
        // tinh gia tri mo duong huyet
        if (dh < dl.duongHuyetMin) {
            duongHuyet.thap = 1;
        } else if (dh >= dl.duongHuyetMin && dh < (dl.duongHuyetMin + dl.duongHuyetMax) / 2) {
            duongHuyet.binhThuong = 2 * (dh - dl.duongHuyetMin) / (dl.duongHuyetMax - dl.duongHuyetMin);
            duongHuyet.thap = 1 - duongHuyet.binhThuong;
        } else if (dh >= (dl.duongHuyetMin + dl.duongHuyetMax) / 2 && dh < dl.duongHuyetMax) {
            duongHuyet.binhThuong = 2 * (dl.duongHuyetMax - dh) / (dl.duongHuyetMax - dl.duongHuyetMin);
            duongHuyet.cao = 1 - duongHuyet.binhThuong;
        } else if (dh >= dl.duongHuyetMax) {
            duongHuyet.cao = 1;
        }
        duongHuyet.set();
        // tinh gia tri mo nhip tim
        if (nt < dl.nhipTimMin) {
            nhiptim.thap = 1;
        } else if (nt >= dl.nhipTimMin && nt < (dl.nhipTimMin + dl.nhipTimMax) / 2) {
            nhiptim.binhThuong = 2 * (nt - dl.nhipTimMin) / (dl.nhipTimMax - dl.nhipTimMin);
            nhiptim.thap = 1 - nhiptim.binhThuong;
        } else if (nt >= (dl.nhipTimMin + dl.nhipTimMax) / 2 && dh < dl.nhipTimMax) {
            nhiptim.binhThuong = 2 * (dl.nhipTimMax - nt) / (dl.nhipTimMax - dl.nhipTimMin);
            nhiptim.cao = 1 - nhiptim.binhThuong;
        } else if (nt >= dl.nhipTimMax) {
            nhiptim.cao = 1;
        }
        nhiptim.set();
        // tinh gia tri mo cholesterol
        if (choles < dl.cholesterolMin) {
            cholesterol.thap = 1;
        } else if (choles >= dl.cholesterolMin && choles < (dl.cholesterolMin + dl.cholesterolMax) / 2) {
            cholesterol.binhThuong = 2 * (choles - dl.cholesterolMin) / (dl.cholesterolMax - dl.cholesterolMin);
            cholesterol.thap = 1 - cholesterol.binhThuong;
        } else if (choles >= (dl.cholesterolMin + dl.cholesterolMax) / 2 && choles < dl.cholesterolMax) {
            cholesterol.binhThuong = 2 * (dl.cholesterolMax - choles) / (dl.cholesterolMax - dl.cholesterolMin);
            cholesterol.cao = 1 - cholesterol.binhThuong;
        } else if (choles >= dl.cholesterolMax) {
            cholesterol.cao = 1;
        }
        cholesterol.set();
        // thap = 0, bt = 1, cao = 2, duong huyet, chieu cao, can nang
        listsdd2.add(new Pair(1, 0, 0));
        listsdd2.add(new Pair(0, 0, 1));
        listsdd2.add(new Pair(0, 1, 0));
        listsdd2.add(new Pair(0, 2, 0));
        listsdd2.add(new Pair(0, 0, 0));
        listsdd1.add(new Pair(1, 1, 0));
        listsdd1.add(new Pair(1, 2, 0));
        listsdd1.add(new Pair(1, 0, 1));
        listsdd1.add(new Pair(0, 1, 1));
        listsdd1.add(new Pair(0, 2, 1));
        listsdd1.add(new Pair(2, 0, 0));
        listbt.add(new Pair(1, 1, 1));
        listbt.add(new Pair(1, 2, 1));
        listbt.add(new Pair(0, 1, 2));
        listbt.add(new Pair(0, 2, 2));
        listbt.add(new Pair(2, 0, 1));
        listbt.add(new Pair(2, 1, 0));
        listbt.add(new Pair(2, 2, 0));
        listtc.add(new Pair(1, 1, 2));
        listtc.add(new Pair(1, 2, 2));
        listtc.add(new Pair(0, 0, 2));
        listtc.add(new Pair(2, 1, 1));
        listtc.add(new Pair(2, 2, 1));
        listbp.add(new Pair(1, 0, 2));
        listbp.add(new Pair(2, 0, 2));
        listbp.add(new Pair(2, 1, 2));
        listbp.add(new Pair(2, 2, 2));
        // thap = 0, bt = 1, cao = 2, duonghuyet, choles, nhip tim

        listtuanhoan_k.add(new Pair(1, 0, 0));
        listtuanhoan_k.add(new Pair(1, 1, 0));
        listtuanhoan_k.add(new Pair(1, 0, 1));
        listtuanhoan_k.add(new Pair(0, 0, 0));
        listtuanhoan_k.add(new Pair(0, 2, 0));
        listtuanhoan_k.add(new Pair(0, 1, 0));
        listtuanhoan_k.add(new Pair(0, 0, 1));
        listtuanhoan_k.add(new Pair(0, 1, 1));
        listtuanhoan_k.add(new Pair(0, 0, 2));
        listtuanhoan_k.add(new Pair(2, 0, 0));

        listtuanhoan_bt.add(new Pair(1, 2, 0));
        listtuanhoan_bt.add(new Pair(1, 1, 1));
        listtuanhoan_bt.add(new Pair(1, 0, 2));
        listtuanhoan_bt.add(new Pair(0, 2, 1));
        listtuanhoan_bt.add(new Pair(0, 1, 2));
        listtuanhoan_bt.add(new Pair(2, 1, 0));
        listtuanhoan_bt.add(new Pair(2, 0, 1));
        listtuanhoan_bt.add(new Pair(2, 0, 2));
        listtuanhoan_bt.add(new Pair(2, 1, 2));

        listtuanhoan_t.add(new Pair(1, 2, 1));
        listtuanhoan_t.add(new Pair(1, 2, 2));
        listtuanhoan_t.add(new Pair(1, 1, 2));
        listtuanhoan_t.add(new Pair(0, 2, 2));
        listtuanhoan_t.add(new Pair(2, 2, 0));
        listtuanhoan_t.add(new Pair(2, 1, 1));
        listtuanhoan_t.add(new Pair(2, 2, 1));
        listtuanhoan_t.add(new Pair(2, 2, 2));
    }
}
// fuzzy.ketqua("nam", 1, 80, 10, 6); 0.0 0.5333328 0.46666718 0.22727275 0.0
// fuzzy.ketqua("nam", 1, 90, 15, 12); 0.0 0.0 0.0 0.0 1.0
// fuzzy.ketqua("nam", 1, 80, 9, 4); 0.6904764 0.13333385 0.0 0.0 0.0
// fuzzy.ketqua("nu", 4, 100, 20, 6); 0.31395352 0.32608697 0.673913 0.31395352 0.22727275