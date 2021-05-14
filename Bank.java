import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Bank {




    private static JFrame mainFrame;
    private static JPanel mainPanel, loginPanel, detailsPanel, buttonsPanel;
    private static JLabel loginAccLabel;
    private static JTextField loginField;
    private static JButton loginButton;

    private static JLabel holderNameLabel;
    private static JTextField holderNameField;
    private static JLabel accountNumberLabel;
    private static JTextField accountNumberField;
    private static JLabel balanceLabel;
    private static JTextField balanceField;

    private static JButton depositButton, withdrawButton, transferButton, writeButton;


    private static Account owner = null;

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Banking App");
        frame.getContentPane().setBackground(Color.red);

        frame.pack();


// load all accounts
        ArrayList<Account> accounts = loadAccounts();





        mainPanel = new JPanel(new GridLayout(2, 1));

        loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginAccLabel = new JLabel("Enter account number: ");
        loginField = new JTextField(10);
        loginButton = new JButton("Log In");




        loginPanel.add(loginAccLabel);
        loginPanel.add(loginField);
        loginPanel.add(loginButton);

        detailsPanel = new JPanel(new GridLayout(3, 2));
        holderNameLabel = new JLabel("Account Name: ");
        holderNameField = new JTextField(20);
        holderNameField.setEditable(false);

        accountNumberLabel = new JLabel("Account Number: ");
        accountNumberField = new JTextField(25);
        accountNumberField.setEditable(false);
        balanceLabel = new JLabel("Balance: ");
        balanceField = new JTextField(10);
        balanceField.setEditable(false);

        detailsPanel.add(holderNameLabel);
        detailsPanel.add(holderNameField);
        detailsPanel.add(accountNumberLabel);
        detailsPanel.add(accountNumberField);
        detailsPanel.add(balanceLabel);
        detailsPanel.add(balanceField);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");

        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        transferButton.setEnabled(false);


        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(transferButton);


        mainPanel.add(loginPanel);
        mainPanel.add(detailsPanel);
        mainPanel.add(buttonsPanel);

        frame.add(mainPanel);
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


// action listener for log in button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginField.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "No transfer number entered.");
                else
                {
                    String accNum = loginField.getText().trim();
                    if(searchAccount(accounts, accNum) == -1)
                        JOptionPane.showMessageDialog(null, "No accounts found for account number: " + accNum);
                    else
                    {
                        owner = accounts.get(searchAccount(accounts, accNum));
                        holderNameField.setText(owner.getaccountName());
                        accountNumberField.setText(owner.getAccountNumber());
                        balanceField.setText(String.format("$ %.2f", owner.getBalance()));

                        depositButton.setEnabled(true);
                        withdrawButton.setEnabled(true);
                        transferButton.setEnabled(true);

                    }
                }
            }
        });

// action listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = JOptionPane.showInputDialog("Deposit Amount:");
                while(!isDouble(inp))
                {
                    inp = JOptionPane.showInputDialog("Deposit Amount:");
                }
                double amount = Double.parseDouble(inp);
                owner.deposit(amount);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

// action listener for withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = JOptionPane.showInputDialog("Withdraw Amount:");
                while(!isDouble(inp))
                {
                    inp = JOptionPane.showInputDialog("Withdraw Amount:");
                }
                double amount = Double.parseDouble(inp);
                owner.withdraw(amount);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

// action listener for transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accNum = JOptionPane.showInputDialog("Transfer Account Number:");
                if(accNum.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Enter transfer account number.");
                    return;
                }
                int index = searchAccount(accounts, accNum);
                if(index == -1)
                {
                    JOptionPane.showMessageDialog(null, "Account Number Does Not Have an Account: " + accNum);
                    return;
                }

                Account transfer = accounts.get(index);
                double inp = Double.parseDouble(JOptionPane.showInputDialog("Transfer Amount:"));
                owner.transfer(transfer, inp);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

// action listener for write button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }

    private static ArrayList<Account> loadAccounts()
    {
        ArrayList<Account> accs = new ArrayList<>();

        accs.add(new Checking("Grant", "1234", 10000));

        accs.add(new Checking("Miriam", "4321", 8000));

        accs.add(new Savings("Dave", "6789", 4000));


        return accs;
    }

    private static int searchAccount(ArrayList<Account> accounts, String accountNumber)
    {
        int index = -1;

        for(int i = 0; i < accounts.size(); i++)
        {
            if(accounts.get(i).getAccountNumber().equals(accountNumber))
            {
                index = i;
                break;
            }
        }
        return index;
    }



    private static boolean isDouble(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, s + "Please Enter a Number");
            return false;
        }
    }
}
