import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private double MASS = 1;
    private double PLANCK = 1;//1.05e-34;
    private double Uo = 1;
    private double A = 1;//1e-34;

    private double accuracy = 0.01;

    private final List<Double> tgList = new ArrayList<>();
    private final List<Double> ctgList = new ArrayList<>();

    private final Map<Double, Double> tgBMap = new HashMap<>();
    private final Map<Double, Double> tgCMap = new HashMap<>();

    private final Map<Double, Double> ctgBMap = new HashMap<>();
    private final Map<Double, Double> ctgCMap = new HashMap<>();

    public Map<Double, Double> getCtgBMap() {
        return ctgBMap;
    }

    public Map<Double, Double> getCtgCMap() {
        return ctgCMap;
    }

    public Map<Double, Double> getTgBMap() {
        return tgBMap;
    }

    public Map<Double, Double> getTgCMap() {
        return tgCMap;
    }

    public double getMASS() {
        return MASS;
    }

    public double getPLANCK() {
        return PLANCK;
    }

    public double getUo() {
        return Uo;
    }

    public double getA() {
        return A;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public List<Double> getTgList() {
        return tgList;
    }

    public List<Double> getCtgList() {
        return ctgList;
    }

    public void setMASS(double MASS) {
        this.MASS = MASS;
    }

    public void setPLANCK(double PLANCK) {
        this.PLANCK = PLANCK;
    }

    public void setUo(double uo) {
        Uo = uo;
    }

    public void setA(double a) {
        A = a;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
