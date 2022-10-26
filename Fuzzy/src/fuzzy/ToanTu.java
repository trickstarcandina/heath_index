/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caotu
 */
public class ToanTu {
    public float thap;
    public float binhthuong;
    public float cao;
    
    public List<Float>p = new ArrayList<>();

    public ToanTu() {
        thap = 0;
        binhthuong = 0;
        cao = 0;
    }
    
    public void set(){
        p.add(thap);
        p.add(binhthuong);
        p.add(cao);
    }
    
    public float getThap() {
        return thap;
    }

    public void setThap(float thap) {
        this.thap = thap;
    }

    public float getBinhthuong() {
        return binhthuong;
    }

    public void setBinhthuong(float binhthuong) {
        this.binhthuong = binhthuong;
    }

    public float getCao() {
        return cao;
    }

    public void setCao(float cao) {
        this.cao = cao;
    }

    @Override
    public String toString() {
        return "ToanTu{" + "thap=" + thap + ", binhthuong=" + binhthuong + ", cao=" + cao + '}';
    }
    
    
    
}
