import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class bank extends JFrame {

    bank() {
        super("Banking Transfer");
        JLabel title = new JLabel("Banking Transfer", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        add(title, BorderLayout.NORTH);
        Container c = getContentPane();
        c.setBackground(Color.LIGHT_GRAY);

        Container content = getContentPane();
        LayoutManager layout = new GridLayout(20, 500);
        content.setLayout(layout);

    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                bank gui = new bank();
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setVisible(true);
                                                   }
                                               }
        );
    }
}

