import org.apache.commons.math3.linear.*;

import static java.lang.Math.*;


public class Solver {

    private double MASS = 1;
    private double PLANCK = 1;
    private double Uo = 1;
    private double A = 5; //менять тут

    private double accuracy = 0.01;

    private double funcTg(double E){
        double tmp =tan( sqrt( 2*MASS*(E + Uo) )*A / (2 * PLANCK) );

        float tmp1 = (float) (1 / ( sqrt(Uo / abs(E) -1) ));

        return tmp - tmp1;
    }

    private double funcCtg(double E){
        double arg = sqrt( 2*MASS*(E + Uo) )*A / (2 * PLANCK);

        double tmp = -cos(arg) / sin(arg);
        double tmp1 = 1 / ( sqrt(1 / abs(E) -1) );
        return tmp - tmp1;
    }

    public Solver(){
        Context context = new Context();

        context.setA(A);
        context.setUo(Uo);
        context.setPLANCK(PLANCK);
        context.setMASS(MASS);
        context.setAccuracy(accuracy);


        for (double i = -0.001; i > -1; i -= 0.001){
            double tmp = funcCtg(i);
            if (abs(tmp) < accuracy ) {
                System.out.println("ctg: "+i);
                context.getCtgList().add(i);
                findBCctg(i, context);
                i -= 0.1;
            }
        }

        for (double i = -0.001; i > -1; i -= 0.001){
            double tmp1 = funcTg(i);
            if (abs(tmp1) < accuracy ) {
                System.out.println("tg: "+i);
                context.getTgList().add(i);
                findBCtg(i, context);
                i -= 0.1;
            }
        }

        new Graphs(context);

    }

    private void findBCtg(double E, Context context){
        double k1 = sqrt(2*MASS*(E + Uo))/PLANCK;
        double k2 = sqrt(2*MASS*abs(E))/PLANCK;

        double v11 = ((A * k1) + cos(A*k1))/(2*k1);
        double v12 = exp(-A * k1)/k2;
        double v21 = 1;

        double v22tmp = cos(k1*A/2);
        double v22 = -exp(-A * k2)/(v22tmp*v22tmp);

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] {{ v11, v12 }, { v21, v22 }}, false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(new double[] { 1, 0 }, false);
        RealVector solution = solver.solve(constants);
        //System.out.println(E+": " +solution);

        double B = sqrt(solution.getEntry(0));
        double C = sqrt(solution.getEntry(1));

        double tmp = B;
        double tmp1 = C*exp(-k2*A/2);

        tmp *= cos(k1 * A / 2);
        if (signum(tmp) != signum(tmp1)) {
            B = -B;
        }
        context.getTgBMap().put(E, B);
        context.getTgCMap().put(E, C);
    }

    private void findBCctg(double E, Context context){
        double k1 = sqrt(2*MASS*(E + Uo))/PLANCK;
        double k2 = sqrt(2*MASS*abs(E))/PLANCK;

        double v11 = ((A * k1) - sin(A*k1))/(2*k1);
        double v12 = exp(-A * k1)/k2;
        double v21 = 1;

        double v22tmp = sin(k1*A/2);
        double v22 = -exp(-A * k2)/(v22tmp*v22tmp);

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] {{ v11, v12 }, { v21, v22 }}, false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(new double[] { 1, 0 }, false);
        RealVector solution = solver.solve(constants);
        //System.out.println(E+": " +solution);

        double B = sqrt(solution.getEntry(0));
        double C = sqrt(solution.getEntry(1));

        double tmp = B;
        double tmp1 = C*exp(-k2*A/2);

        tmp *= sin(k1 * A / 2);
        if (signum(tmp) != signum(tmp1)) {
            //System.out.println(E + "- nechet, not equal");
            B = -B;
        }
        context.getCtgBMap().put(E, B);
        context.getCtgCMap().put(E, C);
        //System.out.println(E+": B="+B+" C="+C);
        //System.out.println(E+": k1="+k1+" k2="+k2);
    }

}