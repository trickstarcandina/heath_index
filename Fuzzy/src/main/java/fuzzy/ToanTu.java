package fuzzy;

import java.util.ArrayList;
import java.util.List;

public class ToanTu {
        public double thap;
        public double binhthuong;
        public double cao;

        public List<Double>p = new ArrayList<>();

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

    public double getThap() {
        return thap;
    }

    public void setThap(double thap) {
        this.thap = thap;
    }

    public double getBinhthuong() {
        return binhthuong;
    }

    public void setBinhthuong(double binhthuong) {
        this.binhthuong = binhthuong;
    }

    public double getCao() {
        return cao;
    }

    public void setCao(double cao) {
        this.cao = cao;
    }

    public List<Double> getP() {
        return p;
    }

    public void setP(List<Double> p) {
        this.p = p;
    }

    @Override
        public String toString() {
            return "ToanTu{" + "thap=" + thap + ", binhthuong=" + binhthuong + ", cao=" + cao + '}';
        }
}
