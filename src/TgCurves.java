import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import static java.lang.Math.*;

public class TgCurves {
    private final Context context;
    private ChartPanel panel;

    public TgCurves(Context context){
        this.context = context;
        drawCurves();
    }

    private void drawCurves(){
//        XYSeries series = new XYSeries(0);
//        XYSeries seriesOne = new XYSeries(1);
//
//        XYSeries series1 = new XYSeries(2);
//        XYSeries series1One = new XYSeries(3);

        XYSeriesCollection collection = new XYSeriesCollection();

        for (int i = 0; i != context.getTgList().size(); i++){

            XYSeries series = new XYSeries(i*10);

            double a = context.getA();
            double E = context.getTgList().get(i);

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
                        false, true, true);

        panel = new ChartPanel(chart);
        panel.setSize(500, 500);

//        for(double i = -1.5708; i < 1.5708; i+=0.01){
//
//            tmp = Functions.calculateIntensity(i, context.getN(), context, 1);
//            if (context.isSecondGraph()) {
//                tmp1 = Functions.calculateIntensity(i, context.getN(), context, 2);
//                context.getArray1().add(tmp1);
//                context.setMaxI1(max(tmp1, context.getMaxI1()));
//            }
//
//            context.getArray().add(tmp);
//            context.setMaxI(max(tmp, context.getMaxI()));
//
//            int n = context.getN();
//            if (context.isExtraGraph()) {
//                seriesOne.add(i, Functions.calculateIntensity(i, 1, context, 1) * n * n);
//                if (context.isSecondGraph()) series1One.add(i, Functions.calculateIntensity(i, 1, context, 2) * n * n);
//            }
//            series.add(i, tmp);
//            if (context.isSecondGraph()) series1.add(i, tmp1);
//        }
//
//        JFreeChart chart = ChartFactory
//                .createXYLineChart("", "Theta θ", "Intensity",
//                        collection,
//                        PlotOrientation.VERTICAL,
//                        false, true, true);
//
//        XYPlot plot = (XYPlot) chart.getPlot();
//
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
////        renderer.setSeriesStroke(2, new BasicStroke(
////                1.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
////                1.0f, new float[]{0.5f, 10.0f}, 0.0f));
//
//        //renderer.setPaint(new Color(255, 90, 0));
//        renderer.setSeriesPaint(0, new Color(0, 78, 189));
//        renderer.setSeriesPaint(2, new Color(0, 78, 189));
//        renderer.setSeriesPaint(1, new Color(232, 33, 86));
//        renderer.setSeriesPaint(3, new Color(232, 33, 86));
//
//        plot.setRenderer(renderer);
//
//        chartPanel = new ChartPanel(chart);
    }

    public ChartPanel getPanel() {
        return panel;
    }
}
