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
        b1=new JButton("OK");

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

        l1.setBounds(0,30,100,30);
        t1.setBounds(110,30,100,30);
        l2.setBounds(0,70,100,30);
        t2.setBounds(110,70,100,30);
        b1.setBounds(0,120,100,30);

        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        f1.dispose();
        Project1 p2=new Project1();
    }

    public static void main(String args[])
    {
        quz p1=new quz();
    }
}

//Second JFrame


class Project1 implements ActionListener {
    JFrame f1 = new JFrame("Banking");


    JButton b0;
    JLabel l3;
    JLabel l4;
    JButton b2;

    Project1() {
        JLabel l3 = new JLabel("Online Banking", SwingConstants.CENTER);
        l3.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel l4 = new JLabel("Choose an account:");
        l4.setFont(new Font("Serif", Font.BOLD, 15));


        b0 = new JButton("Checking");
        b2 = new JButton("Add a new account");

        f1.setSize(400, 400);
        f1.setVisible(true);
        f1.setLayout(null);

        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        f1.add(l3);
        f1.add(l4);
        f1.add(b2);

        f1.getContentPane().add(b0);
        Container c = f1.getContentPane();
        c.setBackground(Color.LIGHT_GRAY);


        b0.setBounds(145, 100, 100, 30);
        b2.setBounds(110, 300, 200, 30);


        l3.setBounds(100, 10, 200, 30);
        l4.setBounds(130, 50, 200, 30);

        b0.addActionListener(this);

    }



    public void actionPerformed(ActionEvent e)
    {
        f1.dispose();
        Project2 p2=new Project2();
    }

    public static void main(String args[])
    {
        quz p3=new quz();
    }


}


//third JFrame

class Project2 implements ActionListener {
    JFrame f1=new JFrame("Online banking Log In");
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b5;
    JLabel l3,l4;

    Project2()
    {

        l1=new JLabel("Withdraw");
        l2=new JLabel("Deposit");
        l3=new JLabel("$");
        l4=new JLabel("$");

        t1=new JTextField(20);
        t2=new JTextField(20);
        b5=new JButton("Add a new account");

        f1.setSize(400,400);
        f1.setVisible(true);
        f1.setLayout(null);

        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f1.add(l1);
        f1.add(t1);
        f1.add(l2);
        f1.add(t2);
        f1.add(l3);
        f1.add(l4);

        f1.getContentPane().add(b5);
        Container c = f1.getContentPane();
        c.setBackground(Color.LIGHT_GRAY);

        l1.setBounds(230,30,100,30);
        t1.setBounds(110,30,100,30);
        l2.setBounds(230,70,100,30);
        t2.setBounds(110,70,100,30);
        b5.setBounds(70,300,300,30);

        l3.setBounds(100,30,100,30);
        l4.setBounds(100,67,100,30);

        b5.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e)
    {
        f1.dispose();
        Project3 p3=new Project3();
    }

    public static void main(String ag[])
    {
        quz p4=new quz();
    }
}

//Second JFrame


class Project3
{
    JFrame f1=new JFrame("New Account");
    JLabel l1,l2;
    JTextField t1,t2;
    JButton b1;

    Project3()
    {
        l1=new JLabel("Account Name:");
        l2=new JLabel("Initial Deposit:");
        t1=new JTextField(20);
        t2=new JTextField(20);
        b1=new JButton("Create Account");

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

        l1.setBounds(0,30,100,30);
        t1.setBounds(110,30,100,30);
        l2.setBounds(0,70,100,30);
        t2.setBounds(110,70,100,30);
        b1.setBounds(50,120,200,30);




        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setSize(400,400);
    }
}


