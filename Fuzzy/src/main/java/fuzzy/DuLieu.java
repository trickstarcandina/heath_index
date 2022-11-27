package fuzzy;

public class DuLieu {
    public String gioitinh;
    public int tuoi;
    public double chieucaomin;
    public double chieucaomax;
    public double cannangmin;
    public double cannangmax;
    public double duonghuyetmin;
    public double duonghuyetmax;
    public int nhiptimmin;
    public int nhiptimmax;
    public double cholesteromin;
    public double cholesteromax;

    public DuLieu() {
    }

    public DuLieu(String gioitinh, int tuoi, double chieucaomin, double chieucaomax, double cannangmin, double cannangmax, double duonghuyetmin, double duonghuyetmax, int nhiptimmin, int nhiptimmax, double cholesteromin, double cholesteromax) {
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
        this.chieucaomin = chieucaomin;
        this.chieucaomax = chieucaomax;
        this.cannangmin = cannangmin;
        this.cannangmax = cannangmax;
        this.duonghuyetmin = duonghuyetmin;
        this.duonghuyetmax = duonghuyetmax;
        this.nhiptimmin = nhiptimmin;
        this.nhiptimmax = nhiptimmax;
        this.cholesteromin = cholesteromin;
        this.cholesteromax = cholesteromax;
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

    public double getChieucaomin() {
        return chieucaomin;
    }

    public void setChieucaomin(double chieucaomin) {
        this.chieucaomin = chieucaomin;
    }

    public double getChieucaomax() {
        return chieucaomax;
    }

    public void setChieucaomax(double chieucaomax) {
        this.chieucaomax = chieucaomax;
    }

    public double getCannangmin() {
        return cannangmin;
    }

    public void setCannangmin(double cannangmin) {
        this.cannangmin = cannangmin;
    }

    public double getCannangmax() {
        return cannangmax;
    }

    public void setCannangmax(double cannangmax) {
        this.cannangmax = cannangmax;
    }

    public double getDuonghuyetmin() {
        return duonghuyetmin;
    }

    public void setDuonghuyetmin(double duonghuyetmin) {
        this.duonghuyetmin = duonghuyetmin;
    }

    public double getDuonghuyetmax() {
        return duonghuyetmax;
    }

    public void setDuonghuyetmax(double duonghuyetmax) {
        this.duonghuyetmax = duonghuyetmax;
    }

    public int getNhiptimmin() {
        return nhiptimmin;
    }

    public void setNhiptimmin(int nhiptimmin) {
        this.nhiptimmin = nhiptimmin;
    }

    public int getNhiptimmax() {
        return nhiptimmax;
    }

    public void setNhiptimmax(int nhiptimmax) {
        this.nhiptimmax = nhiptimmax;
    }

    public double getCholesteromin() {
        return cholesteromin;
    }

    public void setCholesteromin(double cholesteromin) {
        this.cholesteromin = cholesteromin;
    }

    public double getCholesteromax() {
        return cholesteromax;
    }

    public void setCholesteromax(double cholesteromax) {
        this.cholesteromax = cholesteromax;
    }

    @Override
    public String toString() {
        return "DuLieu{" +
                "gioitinh='" + gioitinh + '\'' +
                ", tuoi=" + tuoi +
                ", chieucaomin=" + chieucaomin +
                ", chieucaomax=" + chieucaomax +
                ", cannangmin=" + cannangmin +
                ", cannangmax=" + cannangmax +
                ", duonghuyetmin=" + duonghuyetmin +
                ", duonghuyetmax=" + duonghuyetmax +
                ", nhiptimmin=" + nhiptimmin +
                ", nhiptimmax=" + nhiptimmax +
                ", cholesteromin=" + cholesteromin +
                ", cholesteromax=" + cholesteromax +
                '}';
    }
}
