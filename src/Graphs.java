import javax.swing.*;

public class Graphs {
    private Context context;

    JFrame frame = new JFrame("Pit");
    JFrame frameTg = new JFrame("Symmetric");
    JFrame frameCtg = new JFrame("Antisymmetric");

    public Graphs(Context context) {
        this.context = context;
        drawPit();
        drawCurves();
    }

    private void drawPit(){
        frame.getContentPane().add(new CustomGraphBuilder(context));
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void drawCurves(){
        TgCurves curves = new TgCurves(context);
        frameTg.getContentPane().add(curves.getPanel());
        frameTg.setVisible(true);
        frameTg.setSize(500, 400);
        frameTg.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameTg.setLocation(510, 0);

        CtgCurves curves1 = new CtgCurves(context);
        frameCtg.getContentPane().add(curves1.getPanel());
        frameCtg.setVisible(true);
        frameCtg.setSize(500, 400);
        frameCtg.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameCtg.setLocation(510, 410);
    }
}
