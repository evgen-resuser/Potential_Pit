import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.Math.*;

public class CustomGraphBuilder extends JPanel {

    private final Context context;

    public CustomGraphBuilder(Context context){
        this.setVisible(true);
        this.setSize(500, 500);

        this.context = context;
    }

    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        BasicStroke stroke = new BasicStroke(1f);

        g2d.setColor(Color.RED);
        g2d.setStroke(stroke);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Double> listTg = context.getTgList();
        List<Double> listCtg = context.getCtgList();

        g2d.setColor(Color.BLACK);

        g2d.drawLine(0, 20, 150, 20);
        g2d.drawString("0", 130, 15);
        g2d.drawLine(150, 20, 150, 450);
        g2d.drawString("-Uo", 120, 450);
        g2d.drawLine(150, 450, 350, 450);
        g2d.drawLine(350, 450, 350, 20);
        g2d.drawLine(350, 20, 500, 20);

        g2d.setColor(Color.RED);

        g2d.drawString("Симметричн. (tg)", 20, 410);

        for (double E : listTg){
            int dot = 430 - (int)(abs(E) * 430) + 20;
            g2d.drawLine(150, dot, 350, dot);
            g2d.drawString(Double.toString((double) round(E * 100) /100), 360, dot);
        }

        g2d.setColor(Color.BLUE);

        g2d.drawString("Антисимм. (ctg)", 20, 430);

        for (double E : listCtg){
            int dot = 430 - (int)(abs(E) * 430) + 20;
            g2d.drawLine(150, dot, 350, dot);
            g2d.drawString(Double.toString((double) round(E * 100) /100), 360, dot);
        }

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        draw(g);
    }

}