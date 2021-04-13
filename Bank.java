import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class quz implements ActionListener
{
    JFrame f1=new JFrame("Online banking Log In");
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b1;

    quz()
    {
        l1=new JLabel("User Name");
        l2=new JLabel("Password");
        t1=new JTextField(20);
        t2=new JTextField(20);
        b1=new JButton("Log in");

        f1.setSize(400,400);
        f1.setVisible(true);
        f1.setLayout(null);

        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f1.add(l1);
        f1.add(t1);
        f1.add(l2);
        f1.add(t2);

        f1.getContentPane().add(b1);
        Container c = f1.getContentPane();
        c.setBackground(Color.LIGHT_GRAY);

        l1.setBounds(90,50,100,30);
        t1.setBounds(200,50,100,30);
        l2.setBounds(90,90,100,30);
        t2.setBounds(200,90,100,30);
        b1.setBounds(130,160,100,30);

        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        f1.dispose();
        Project1 p2=new Project1();
    }

    public static void main(String ag[])
    {
        quz p1=new quz();
    }
}

//Second JFrame


class Project1
{
    JFrame f1=new JFrame("Banking");

    Project1()
    {
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setSize(400,400);
    }
}
