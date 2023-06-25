import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import java.awt.*;

import static java.lang.Math.*;

public class TgCurves {
    private final Context context;
    private ChartPanel panel;

    public TgCurves(Context context){
        this.context = context;
        drawCurves();
    }

    private void drawCurves(){

        XYSeriesCollection collection = new XYSeriesCollection();

        double a = context.getA();

        XYSeries pit = new XYSeries("pit");
        pit.add(-a*2, 0.001);
        pit.add(-a*2, 0);
        pit.add(-a/2, 0);
        pit.add(-a/2, -1);
        pit.add(a/2, -1);
        pit.add(a/2, 0);
        pit.add(a*2, 0);



        collection.addSeries(pit);

        for (int i = 0; i != context.getTgList().size(); i++){

            double E = context.getTgList().get(i);

            XYSeries series = new XYSeries(Double.toString((double) round(E * 100) /100));



            double b = context.getTgBMap().get(E);
            double c = context.getTgCMap().get(E);

            double k1 = sqrt(2*context.getMASS()*(E + context.getUo()))/context.getPLANCK();
            double k2 = sqrt(2*context.getMASS()*abs(E))/context.getPLANCK();

            for (double j = -a * 2; j < -a/2; j+= 0.01){
                series.add(j,
                        c*exp(k2*j));
            }

            for (double j = -a/2; j < a/2; j+= 0.01){
                series.add(j, b * cos(k1*j));
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
                        true, true, true);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.BLACK);

        plot.setRenderer(renderer);

        panel = new ChartPanel(chart);
        panel.setSize(500, 500);

    }

    public ChartPanel getPanel() {
        return panel;
    }
}
