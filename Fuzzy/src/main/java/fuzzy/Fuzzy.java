/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author caotu
 */
public class Fuzzy {
    public List<DuLieu> list = new ArrayList<DuLieu>(); // danh sach du lieu so sanh
    public ToanTu chieucao = new ToanTu();
    public ToanTu cannang = new ToanTu();
    public ToanTu duonghuyet = new ToanTu();
    public ToanTu nhiptim = new ToanTu();
    public ToanTu cholesterol = new ToanTu();
    // cac truong hop
    public List<Pair>listsdd2 = new ArrayList<>(); // cac cap truong hop xay ra(chieu cao, can nang, duong huyet)
    public List<Pair>listsdd1 = new ArrayList<>();
    public List<Pair>listbt = new ArrayList<>();
    public List<Pair>listtc = new ArrayList<>();
    public List<Pair>listbp = new ArrayList<>();
    public List<Pair>listtuanhoan_k = new ArrayList<>(); // nhip tim, cholesterol, duong huyet
    public List<Pair>listtuanhoan_bt = new ArrayList<>();
    public List<Pair>listtuanhoan_t = new ArrayList<>();
    // gia tri cac truong hop
    public Double p_sdd2;
    public Double p_sdd1;
    public Double p_bt;
    public Double p_tc;
    public Double p_bp;
    public Double th_k;
    public Double th_bt;
    public Double th_t;

    // tim kiem du lieu chuan theo gioi tinh va tuoi
    public DuLieu timkiem(String gioitinh, int tuoi){
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).gioitinh.equals(gioitinh) && list.get(i).getTuoi() == tuoi) return list.get(i);
        return null;
    }

    // kiem tra truong hop thoa man
    public int truonghopthoaman_dinhduong(int x, int y, int z){
        for(int i = 0; i < listsdd2.size(); i++)
            if(x == listsdd2.get(i).x && y == listsdd2.get(i).y && z == listsdd2.get(i).z) return 1;

        for(int i = 0; i < listsdd1.size(); i++)
            if(x == listsdd1.get(i).x && y == listsdd1.get(i).y && z == listsdd1.get(i).z) return 2;

        for(int i = 0; i < listbt.size(); i++)
            if(x == listbt.get(i).x && y == listbt.get(i).y && z == listbt.get(i).z) return 3;

        for(int i = 0; i < listtc.size(); i++)
            if(x == listtc.get(i).x && y == listtc.get(i).y && z == listtc.get(i).z) return 4;

        for(int i = 0; i < listbp.size(); i++)
            if(x == listbp.get(i).x && y == listbp.get(i).y && z == listbp.get(i).z) return 5;

        return 0;
    }

    // kiem tra truong hop thoa man
    public int truonghopthoaman_tuanhoan(int x, int y, int z){
        for(int i = 0; i < listtuanhoan_k.size(); i++)
            if(x == listtuanhoan_k.get(i).x && y == listtuanhoan_k.get(i).y && z == listtuanhoan_k.get(i).z) return 1;
        for(int i = 0; i < listtuanhoan_bt.size(); i++)
            if(x == listtuanhoan_bt.get(i).x && y == listtuanhoan_bt.get(i).y && z == listtuanhoan_bt.get(i).z) return 2;
        for(int i = 0; i < listtuanhoan_t.size(); i++)
            if(x == listtuanhoan_t.get(i).x && y == listtuanhoan_t.get(i).y && z == listtuanhoan_t.get(i).z) return 3;

        return 0;
    }

    // ham and cua 3 toan tu dinh duong
    public double kethopDinhDuong(int x, int y, int z){
        return Double.min(duonghuyet.p.get(x), Double.min(chieucao.p.get(y), cannang.p.get(z)));
    }


    // ham and cua 3 toan tu tuan hoan
    public double kethopTuanHoan(int x, int y, int z){
        return Double.min(duonghuyet.p.get(x), Double.min(cholesterol.p.get(y), nhiptim.p.get(z)));
    }

    // gia tri tap mo, thanh vien tap mo
    public List ketqua(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double cholesterol ){
        init(gioitinh, tuoi, cc, cn, dh, nt, cholesterol );

        p_sdd2 = 0.0; p_sdd1 = 0.0; p_bt = 0.0; p_tc = 0.0; p_bp = 0.0; th_k = 0.0; th_bt = 0.0; th_t = 0.0;

        for(int x = 0; x <= 2; x++)
            for(int y = 0; y <= 2; y++)
                for(int z = 0; z <= 2; z++)
                    switch (truonghopthoaman_dinhduong(x, y, z)) {
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

        for(int x = 0; x <= 2; x++)
            for(int y = 0; y <= 2; y++)
                for(int z = 0; z <= 2; z++)
                    switch (truonghopthoaman_tuanhoan(x, y, z)) {
                        case 1:
                            th_k = Double.max(th_k, kethopTuanHoan(x, y, z));
                            break;
                        case 2:
                            th_bt = Double.max(th_bt, kethopTuanHoan(x, y, z));
                            break;
                        case 3:
                            th_t = Double.max(th_t, kethopTuanHoan(x, y, z));
                            break;
                        default:
                            break;
                    }
        List<Double> res = new ArrayList<>();
        res.add(p_sdd2); res.add(p_sdd1); res.add(p_bt); res.add(p_tc); res.add(p_bp); res.add(th_k); res.add(th_bt); res.add(th_t);
        return res;
    }

    // tinh xac suat 3 toan tu dau vao, liet ke cac truong hop
    public void init(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double choles){
        DuLieu dl = timkiem(gioitinh, tuoi);
        // tinh gia tri mo chieu cao
        if(cc < dl.chieucaomin){
            chieucao.thap = 1;
        } else
        if(cc >= dl.chieucaomin && cc < (dl.chieucaomin+dl.chieucaomax)/2){
            chieucao.binhthuong = 2*(cc-dl.chieucaomin)/(dl.chieucaomax-dl.chieucaomin);
            chieucao.thap = 1-chieucao.binhthuong;
        }else
        if(cc >= (dl.chieucaomin+dl.chieucaomax)/2 && cc < dl.chieucaomax){
            chieucao.binhthuong = 2*(dl.chieucaomax-cc)/(dl.chieucaomax-dl.chieucaomin);
            chieucao.cao = 1-chieucao.binhthuong;
        }else
        if(cc >= dl.chieucaomax) chieucao.cao = 1;
        chieucao.set();
        // tinh gia tri mo can nang
        if(cn < dl.cannangmin){
            cannang.thap = 1;
        } else
        if(cn >= dl.cannangmin && cn < (dl.cannangmin+dl.cannangmax)/2){
            cannang.binhthuong = 2*(cn-dl.cannangmin)/(dl.cannangmax-dl.cannangmin);
            cannang.thap = 1-cannang.binhthuong;
        }else
        if(cn >= (dl.cannangmin+dl.cannangmax)/2 && cn < dl.cannangmax){
            cannang.binhthuong = 2*(dl.cannangmax-cn)/(dl.cannangmax-dl.cannangmin);
            cannang.cao = 1-cannang.binhthuong;
        }else
        if(cn >= dl.cannangmax) cannang.cao = 1;
        cannang.set();
        // tinh gia tri mo duong huyet
        if(dh < dl.duonghuyetmin){
            duonghuyet.thap = 1;
        }else
        if(dh >= dl.duonghuyetmin && dh < (dl.duonghuyetmin+dl.duonghuyetmax)/2){
            duonghuyet.binhthuong = 2*(dh-dl.duonghuyetmin)/(dl.duonghuyetmax-dl.duonghuyetmin);
            duonghuyet.thap = 1- duonghuyet.binhthuong;
        }else
        if(dh >= (dl.duonghuyetmin+dl.duonghuyetmax)/2 && dh < dl.duonghuyetmax){
            duonghuyet.binhthuong = 2*(dl.duonghuyetmax-dh)/(dl.duonghuyetmax-dl.duonghuyetmin);
            duonghuyet.cao = 1 - duonghuyet.binhthuong;
        }else
        if(dh >= dl.duonghuyetmax){
            duonghuyet.cao = 1;
        }
        duonghuyet.set();
        // tinh gia tri mo nhip tim
        if(nt < dl.nhiptimmin){
            nhiptim.thap = 1;
        }else
        if(nt >= dl.nhiptimmin && nt < (dl.nhiptimmin+dl.nhiptimmax)/2){
            nhiptim.binhthuong = 2*(nt-dl.nhiptimmin)/(dl.nhiptimmax-dl.nhiptimmin);
            nhiptim.thap = 1- nhiptim.binhthuong;
        }else
        if(nt >= (dl.nhiptimmin+dl.nhiptimmax)/2 && dh < dl.nhiptimmax){
            nhiptim.binhthuong = 2*(dl.nhiptimmax-nt)/(dl.nhiptimmax-dl.nhiptimmin);
            nhiptim.cao = 1 - nhiptim.binhthuong;
        }else
        if(nt >= dl.nhiptimmax){
            nhiptim.cao = 1;
        }
        nhiptim.set();
        // tinh gia tri mo cholesterol
        if(choles < dl.cholesteromin){
            cholesterol.thap = 1;
        }else
        if(choles >= dl.cholesteromin && choles < (dl.cholesteromin+dl.cholesteromax)/2){
            cholesterol.binhthuong = 2*(choles-dl.cholesteromin)/(dl.cholesteromax-dl.cholesteromin);
            cholesterol.thap = 1 - cholesterol.binhthuong;
        }else
        if(choles >= (dl.cholesteromin+dl.cholesteromax)/2 && choles < dl.cholesteromax){
            cholesterol.binhthuong = 2*(dl.cholesteromax-choles)/(dl.cholesteromax-dl.cholesteromin);
            cholesterol.cao = 1 - cholesterol.binhthuong;
        }else
        if(choles >= dl.cholesteromax){
            cholesterol.cao = 1;
        }
        cholesterol.set();
        // thap = 0, bt = 1, cao = 2, duong huyet, chieu cao, can nang
        listsdd2.add(new Pair(1,0,0)); listsdd2.add(new Pair(0,0,1)); listsdd2.add(new Pair(0,1,0)); listsdd2.add(new Pair(0,2,0));
        listsdd2.add(new Pair(0,0,0));
        listsdd1.add(new Pair(1,1,0)); listsdd1.add(new Pair(1,2,0)); listsdd1.add(new Pair(1,0,1)); listsdd1.add(new Pair(0,1,1));
        listsdd1.add(new Pair(0,2,1)); listsdd1.add(new Pair(2,0,0));
        listbt.add(new Pair(1,1,1)); listbt.add(new Pair(1,2,1)); listbt.add(new Pair(0,1,2)); listbt.add(new Pair(0,2,2));
        listbt.add(new Pair(2,0,1)); listbt.add(new Pair(2,1,0)); listbt.add(new Pair(2,2,0));
        listtc.add(new Pair(1,1,2)); listtc.add(new Pair(1,2,2)); listtc.add(new Pair(0,0,2)); listtc.add(new Pair(2,1,1));
        listtc.add(new Pair(2,2,1));
        listbp.add(new Pair(1,0,2)); listbp.add(new Pair(2,0,2)); listbp.add(new Pair(2,1,2)); listbp.add(new Pair(2,2,2));
        // thap = 0, bt = 1, cao = 2, duonghuyet, choles, nhip tim

        listtuanhoan_k.add(new Pair(1, 0 ,0)); listtuanhoan_k.add(new Pair(1, 1 ,0)); listtuanhoan_k.add(new Pair(1, 0 ,1));
        listtuanhoan_k.add(new Pair(0, 0 ,0)); listtuanhoan_k.add(new Pair(0, 2 ,0)); listtuanhoan_k.add(new Pair(0, 1 ,0));
        listtuanhoan_k.add(new Pair(0, 0 ,1)); listtuanhoan_k.add(new Pair(0, 1 ,1)); listtuanhoan_k.add(new Pair(0, 0 ,2));
        listtuanhoan_k.add(new Pair(2, 0 ,0));

        listtuanhoan_bt.add(new Pair(1, 2, 0)); listtuanhoan_bt.add(new Pair(1, 1, 1)); listtuanhoan_bt.add(new Pair(1, 0, 2));
        listtuanhoan_bt.add(new Pair(0, 2, 1)); listtuanhoan_bt.add(new Pair(0, 1, 2)); listtuanhoan_bt.add(new Pair(2, 1, 0));
        listtuanhoan_bt.add(new Pair(2, 0, 1)); listtuanhoan_bt.add(new Pair(2, 0, 2)); listtuanhoan_bt.add(new Pair(2, 1, 2));

        listtuanhoan_t.add(new Pair(1, 2, 1)); listtuanhoan_t.add(new Pair(1, 2, 2)); listtuanhoan_t.add(new Pair(1, 1, 2));
        listtuanhoan_t.add(new Pair(0, 2, 2)); listtuanhoan_t.add(new Pair(2, 2, 0)); listtuanhoan_t.add(new Pair(2, 1, 1));
        listtuanhoan_t.add(new Pair(2, 2, 1)); listtuanhoan_t.add(new Pair(2, 2, 2));
    }


    // doc du lieu tu file csv
    public Fuzzy() throws FileNotFoundException, IOException { // doc du lieu tu file csv
        String excelFilePath = "E:\\CODE\\Intellij\\Fuzzy\\src\\main\\java\\fuzzy\\input.xlsx";
        list = InputController.read(excelFilePath);
    }

    public static void main(String[] args) throws IOException {
        Fuzzy fuzzy = new Fuzzy();
        List<Double>list = fuzzy. ketqua("nu", 4, 100, 20, 6, 113, 224);
        System.out.println(fuzzy.timkiem("nu", 4).toString());
        System.out.println(fuzzy.chieucao.toString());
        System.out.println(fuzzy.cannang.toString());
        System.out.println(fuzzy.duonghuyet.toString());
        System.out.println(fuzzy.nhiptim.toString());
        System.out.println(fuzzy.cholesterol.toString());
        for(int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.println(fuzzy.list.size());

    }
}
// fuzzy.ketqua("nam", 1, 80, 10, 6); 0.0 0.5333328 0.46666718 0.22727275 0.0
// fuzzy.ketqua("nam", 1, 90, 15, 12); 0.0 0.0 0.0 0.0 1.0
// fuzzy.ketqua("nam", 1, 80, 9, 4); 0.6904764 0.13333385 0.0 0.0 0.0
// fuzzy.ketqua("nu", 4, 100, 20, 6); 0.31395352 0.32608697 0.673913 0.31395352 0.22727275