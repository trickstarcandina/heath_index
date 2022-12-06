/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.chatbotspring.services.fuzzylogic;

import com.example.chatbotspring.services.fuzzylogic.model.DuLieuFuzzy;
import com.example.chatbotspring.services.fuzzylogic.model.Pair;
import com.example.chatbotspring.services.fuzzylogic.model.ToanTu;
import com.example.chatbotspring.services.neuralnetwork.model.DuLieuTraining;
import com.example.chatbotspring.utils.FileReaderCSV;
import com.example.chatbotspring.utils.FileWriterCSV;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void giaTriThanhVien(ToanTu toanTu, double giatri, double canduoi, double cantren){ // tinh 3 gia tri ham thanh vien
        if (giatri < canduoi) {
            toanTu.setThap(1);
        } else if (giatri >= canduoi && giatri < (canduoi + cantren) / 2) {
            toanTu.setBinhThuong(2 * (giatri - canduoi) / (cantren - canduoi));
            toanTu.setThap(1 - toanTu.getBinhThuong());
        } else if (giatri >= (canduoi + cantren) / 2 && giatri < cantren) {
            toanTu.setBinhThuong(2 * (cantren - giatri) / (cantren - canduoi));
            toanTu.setCao(1 - toanTu.getBinhThuong());
        } else if (giatri >= cantren) toanTu.setCao(1);
        toanTu.set();
    }

    // tinh xac suat 3 toan tu dau vao, liet ke cac truong hop
    public void init(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double choles) {
        DuLieuFuzzy dl = timkiem(gioitinh, tuoi);
        // tinh gia tri mo chieu cao
        giaTriThanhVien(chieuCao, cc, dl.getChieuCaoMin(), dl.getChieuCaoMax());
        // tinh gia tri mo can nang
        giaTriThanhVien(canNang, cn, dl.getCanNangMin(), dl.getCanNangMax());
        // tinh gia tri mo duong huyet
        giaTriThanhVien(duongHuyet, dh, dl.getDuongHuyetMin(), dl.getDuongHuyetMax());
        // tinh gia tri mo nhip tim
        giaTriThanhVien(nhipTim, nt, dl.getNhipTimMin(), dl.getNhipTimMax());
        // tinh gia tri mo cholesterol
        giaTriThanhVien(cholesterol, choles, dl.getCholesterolMin(), dl.getCholesterolMax());
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

//    public static void main(String[] args) {
//        Fuzzy fuzzy = new Fuzzy();
//       // List<Double> list = fuzzy.ketQua("nu", 4, 100, 20, 6, 113, 224);
//        List<Double> list = fuzzy.ketQua("nam",	1,	75.5	,9.9,	8,	120,	200);
//        //System.out.println(fuzzy.timkiem("nu", 4).toString());
//        //System.out.println(fuzzy.chieuCao.toString());
//        //System.out.println(fuzzy.canNang.toString());
//        //System.out.println(fuzzy.duongHuyet.toString());
//        //System.out.println(fuzzy.nhipTim.toString());
//        //System.out.println(fuzzy.cholesterol.toString());
//        for (int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
//        System.out.println();
//        System.out.println(fuzzy.moHoa("nu",	9,	132.3,	28.5,	4.95,	72,	178));
//        System.out.println(fuzzy.listDuLieuFuzzy.size());
//        // "nam",	1,	80.5	,10.9,	9.5,	130,	240
//        // "nu",	9,	132.3,	28.5,	4.95,	72,	178
//        // "nam",	1,	75.5	,9.9,	8,	120,	200 (0.0 0.6666666666666666 0.23809523809523794 0.23809523809523794 0.33333333333333337 0.4 0.6 0.0 )0.8310528808867756
//    }

    public double moHoa(String gioitinh, int tuoi, double cc, double cn, double dh, double nt, double cholesterol){ // giai mo theo trong tam
        DuLieuFuzzy dl = timkiem(gioitinh, tuoi);
        double cctb = (double) (dl.getChieuCaoMax() + dl.getChieuCaoMin())/2;
        double cntb = (double) (dl.getCanNangMax() + dl.getCanNangMin())/2;
        double dhtb = (double) (dl.getDuongHuyetMax() + dl.getDuongHuyetMin())/2;
        double nttb = (double) (dl.getNhipTimMax() + dl.getNhipTimMin())/2 ;
        double cholestb = (double) (dl.getCholesterolMax() + dl.getCholesterolMin())/2;
        double x1,x2,x3,x4,x5; // do lech/phan tram
        x1 = (double) 0.3/(cctb-dl.getChieuCaoMin());
        x2 = (double) 0.3/(cntb-dl.getCanNangMin());
        x3 = (double) 0.3/(dhtb-dl.getDuongHuyetMin());
        x4 = (double) 0.3/(nttb-dl.getNhipTimMin());
        x5 = (double) 0.3/(cholestb-dl.getCholesterolMin());
        double l1,l2,l3,l4,l5; // do lech
        l1 = Math.abs(cc - cctb)*x1;
        l2 = Math.abs(cn - cntb)*x2;
        l3 = Math.abs(dh - dhtb)*x3;
        l4 = Math.abs(nt - nttb)*x4;
        l5 = Math.abs(cholesterol - cholestb)*x5;
        //System.out.println(x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);
        //double x = Math.min(x1, Math.min(x2, Math.min(x3, Math.min(x4, x5))));
        double he_so = (Math.random() * (105 - 95 + 1) + 95)/100; // random he so
        double l = (l1+l2+l3+l4+l5)/5;
        return round(Math.abs(1-l*he_so), 3);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

//    public static void main(String[] args) {
//        List<DuLieuFuzzy> listDuLieuFuzzy = FileReaderCSV.readFileFuzzy("src/main/resources/fuzzy.csv");
//        List<DuLieuTraining> duLieuTrainings = FileReaderCSV.readFileTraining("src/main/resources/training.csv");
//        for(DuLieuTraining e : duLieuTrainings) {
//            Fuzzy fuzzy = new Fuzzy();
//            fuzzy.setListDuLieuFuzzy(listDuLieuFuzzy);
//            e.setChiSoSucKhoe(fuzzy.moHoa(
//                    e.getGioiTinh(),
//                    e.getTuoi(),
//                    e.getChieuCao(),
//                    e.getCanNang(),
//                    e.getDuongHuyet(),
//                    e.getNhipTim(),
//                    e.getCholesterol()
//            ));
//        }
//        FileWriterCSV.writeFileTraining(duLieuTrainings, "src/main/resources/training3.csv");
//    }

}
// fuzzy.ketqua("nam", 1, 80, 10, 6); 0.0 0.5333328 0.46666718 0.22727275 0.0
// fuzzy.ketqua("nam", 1, 90, 15, 12); 0.0 0.0 0.0 0.0 1.0
// fuzzy.ketqua("nam", 1, 80, 9, 4); 0.6904764 0.13333385 0.0 0.0 0.0
// fuzzy.ketqua("nu", 4, 100, 20, 6); 0.31395352 0.32608697 0.673913 0.31395352 0.22727275