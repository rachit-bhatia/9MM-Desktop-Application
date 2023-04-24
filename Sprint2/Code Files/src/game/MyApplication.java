package game;
import javax.swing.*;
import java.awt.*;

public class MyApplication extends JFrame {

    public MyApplication() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setPreferredSize(new Dimension(400, 400));

        OvalComponent oval = new OvalComponent();
        mainPanel.add(oval);

        this.add(mainPanel);
        this.pack(); // adjust frame size based on the preferred size of its components
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyApplication();
    }

    private static class OvalComponent extends JComponent {

        @Override
        public void paintComponent(Graphics token) {
            token.setColor(Color.BLUE);
            token.fillOval(50, 50, 100, 100);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
    }
}
