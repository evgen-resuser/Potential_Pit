import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.Math.*;

public class CtgCurves {
    private final Context context;
    private ChartPanel panel;

    public CtgCurves(Context context){
        this.context = context;
        drawCurves();
    }

    private void drawCurves(){
        XYSeriesCollection collection = new XYSeriesCollection();

        for (int i = 0; i != context.getCtgList().size(); i++){

            XYSeries series = new XYSeries(i*20);

            double a = context.getA();
            double E = context.getCtgList().get(i);

            double b = context.getCtgBMap().get(E);
            double c = context.getCtgCMap().get(E);

            double k1 = sqrt(2*context.getMASS()*(E + context.getUo()))/context.getPLANCK();
            double k2 = sqrt(2*context.getMASS()*abs(E))/context.getPLANCK();

            for (double j = -a * 2; j < -a/2; j+= 0.01){
                series.add(j,
                        -c*exp(k2*j));
            }

            for (double j = -a/2; j < a/2; j+= 0.01){
                series.add(j, b * sin(k1*j));
            }

            for (double j = a/2; j < 2*a; j+= 0.01){
                series.add(j,
                        c*exp(-k2*j));
            }

            collection.addSeries(series);

        }

        JFreeChart chart = ChartFactory
                .createXYLineChart("", "", "",
                        collection,
                        PlotOrientation.VERTICAL,
                        false, true, true);

        panel = new ChartPanel(chart);
        panel.setSize(500, 500);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}
