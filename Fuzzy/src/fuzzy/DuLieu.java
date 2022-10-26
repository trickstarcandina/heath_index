/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

/**
 *
 * @author caotu
 */
public class DuLieu {
    public String gioitinh;
    public int tuoi;
    public float chieucaomin;
    public float chieucaomax;
    public float cannangmin;
    public float cannangmax;
    public float duonghuyetmin;
    public float duonghuyetmax;

    public DuLieu() {
    }

    public DuLieu(String gioitinh, int tuoi, float chieucaomin, float chieucaomax, float cannangmin, float cannangmax, float duonghuyetmin, float duonghuyetmax) {
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
        this.chieucaomin = chieucaomin;
        this.chieucaomax = chieucaomax;
        this.cannangmin = cannangmin;
        this.cannangmax = cannangmax;
        this.duonghuyetmin = duonghuyetmin;
        this.duonghuyetmax = duonghuyetmax;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public float getChieucaomin() {
        return chieucaomin;
    }

    public void setChieucaomin(float chieucaomin) {
        this.chieucaomin = chieucaomin;
    }

    public float getChieucaomax() {
        return chieucaomax;
    }

    public void setChieucaomax(float chieucaomax) {
        this.chieucaomax = chieucaomax;
    }

    public float getCannangmin() {
        return cannangmin;
    }

    public void setCannangmin(float cannangmin) {
        this.cannangmin = cannangmin;
    }

    public float getCannangmax() {
        return cannangmax;
    }

    public void setCannangmax(float cannangmax) {
        this.cannangmax = cannangmax;
    }

    public float getDuonghuyetmin() {
        return duonghuyetmin;
    }

    public void setDuonghuyetmin(float duonghuyetmin) {
        this.duonghuyetmin = duonghuyetmin;
    }

    public float getDuonghuyetmax() {
        return duonghuyetmax;
    }

    public void setDuonghuyetmax(float duonghuyetmax) {
        this.duonghuyetmax = duonghuyetmax;
    }

    @Override
    public String toString() {
        return "DuLieu{" + "gioitinh=" + gioitinh + ", tuoi=" + tuoi + ", chieucaomin=" + chieucaomin + ", chieucaomax=" + chieucaomax + ", cannangmin=" + cannangmin + ", cannangmax=" + cannangmax + ", duonghuyetmin=" + duonghuyetmin + ", duonghuyetmax=" + duonghuyetmax + '}';
    }
    
    
    
}
