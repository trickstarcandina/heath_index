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
    public List<DuLieu> list = new ArrayList<DuLieu>();
    public ToanTu chieucao = new ToanTu();
    public ToanTu cannang = new ToanTu();
    public ToanTu duonghuyet = new ToanTu();
    // cac truong hop
    public List<Pair>listsdd2 = new ArrayList<>();
    public List<Pair>listsdd1 = new ArrayList<>();
    public List<Pair>listbt = new ArrayList<>();
    public List<Pair>listtc = new ArrayList<>();
    public List<Pair>listbp = new ArrayList<>();
    // xac suat cac truong hop
    public float p_sdd2, p_sdd1, p_bt, p_tc, p_bp;
    
    // tim kiem du lieu chuan theo gioi tinh va tuoi
    public DuLieu timkiem(String gioitinh, int tuoi){
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).gioitinh.equals(gioitinh) && list.get(i).getTuoi() == tuoi) return list.get(i);
        return null;
    }
    
    // kiem tra truong hop thoa man
    public int truonghopthoaman(int x, int y, int z){
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
    
    // ham and cua 3 toan tu
    public float kethop(int x, int y, int z){
        return Float.min(duonghuyet.p.get(x), Float.min(chieucao.p.get(y), cannang.p.get(z)));
    }
    
    // gia tri tap mo, thanh vien tap mo
    public void ketqua(String gioitinh, int tuoi, float cc, float cn, float dh){
        init(gioitinh, tuoi, cc, cn, dh);
        
        p_sdd2 = 0; p_sdd1 = 0; p_bt = 0; p_tc = 0; p_bp = 0;
        
        for(int x = 0; x <= 2; x++)
            for(int y = 0; y <= 2; y++)
                for(int z = 0; z <= 2; z++)
                    switch (truonghopthoaman(x, y, z)) {
                case 1:
                    p_sdd2 = Float.max(p_sdd2, kethop(x, y, z));
                    break;
                case 2:
                    p_sdd1 = Float.max(p_sdd1, kethop(x, y, z));
                    break;
                case 3:
                    p_bt = Float.max(p_bt, kethop(x, y, z));
                    break;
                case 4:
                    p_tc = Float.max(p_tc, kethop(x, y, z));
                    break;
                case 5:
                    p_bp = Float.max(p_bp, kethop(x, y, z));
                    break;
                default:
                    break;
            } 
    }
    
    // tinh xac suat 3 toan tu dau vao, liet ke cac truong hop
    public void init(String gioitinh, int tuoi, float cc, float cn, float dh ){
        DuLieu dl = timkiem(gioitinh, tuoi);
        // tinh xac suat chieu cao
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
        
        // tinh xac suat can nang
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
         
         // tinh xac suat duong huyet
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
    }
    
    // cat chuoi
    public List<String> getInfor(String line) { // cat chuoi, dau ','
        List<String> result = new ArrayList<>();
        StringBuilder s = new StringBuilder("");
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) != ',') s.append(line.charAt(i)); 
            else {
                result.add(s.toString().trim());
                s = new StringBuilder("");
            }
            
        }
        result.add(s.toString().trim());
        return result;
    }
    
    // doc du lieu tu file csv
    public Fuzzy() throws FileNotFoundException, IOException { // doc du lieu tu file csv
        String path = "E:\\CODE\\Java\\Fuzzy\\src\\input\\dulieu.csv";
        try (BufferedReader bir = new BufferedReader(new FileReader(path))) {
            String line = bir.readLine();
            while (!line.trim().equals("")) {
                List<String> result = getInfor(line);
                if(result.size()>0){
                    DuLieu dl = new DuLieu(result.get(0).trim(), Integer.valueOf(result.get(1).trim()), Float.valueOf(result.get(2).trim())
                                   ,Float.valueOf(result.get(3).trim()), Float.valueOf(result.get(4).trim()), Float.valueOf(result.get(5).trim())
                                   ,Float.valueOf(result.get(6).trim()), Float.valueOf(result.get(7).trim()));
                    list.add(dl);  
             
                }
                line = bir.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) throws IOException {
        Fuzzy fuzzy = new Fuzzy();
        fuzzy.ketqua("nu", 4, 100, 20, 6);
        System.out.println(fuzzy.chieucao.toString());
        System.out.println(fuzzy.cannang.toString());
        System.out.println(fuzzy.duonghuyet.toString());
        System.out.println(fuzzy.p_sdd2 + " " + fuzzy.p_sdd1 + " " + fuzzy.p_bt + " " + fuzzy.p_tc + " " + fuzzy.p_bp);
        System.out.println(fuzzy.list.size());
        
    }
}
// fuzzy.ketqua("nam", 1, 80, 10, 6); 0.0 0.5333328 0.46666718 0.22727275 0.0
// fuzzy.ketqua("nam", 1, 90, 15, 12); 0.0 0.0 0.0 0.0 1.0
// fuzzy.ketqua("nam", 1, 80, 9, 4); 0.6904764 0.13333385 0.0 0.0 0.0
// fuzzy.ketqua("nu", 4, 100, 20, 6); 0.31395352 0.32608697 0.673913 0.31395352 0.22727275