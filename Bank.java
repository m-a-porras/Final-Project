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
    private static JFrame frame;
    private static JPanel mainPanel, loginPanel, detailsPanel, buttonsPanel;
    private static JLabel loginAccLabel, holderNameLabel, accountNumberLabel, balanceLabel, AccTypeLabel;
    private static JTextField loginField, holderNameField, accountNumberField, balanceField, AccTypeField;
    private static JButton loginButton, depositButton, withdrawButton, transferButton, writeButton, fundstransferButton, switchAccountsButton;
    private static Account owner = null;

    public static void main(String[] args) {
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

        detailsPanel = new JPanel(new GridLayout(4, 2));
        holderNameLabel = new JLabel("Account Name: ");
        holderNameField = new JTextField(20);
        holderNameField.setEditable(false);

        accountNumberLabel = new JLabel("Account Number: ");
        accountNumberField = new JTextField(25);
        accountNumberField.setEditable(false);

        balanceLabel = new JLabel("Balance: ");
        balanceField = new JTextField(10);
        balanceField.setEditable(false);

        AccTypeLabel = new JLabel("Account Type: ");
        AccTypeField = new JTextField(25);
        AccTypeField.setEditable(false);

        detailsPanel.add(holderNameLabel);
        detailsPanel.add(holderNameField);
        detailsPanel.add(accountNumberLabel);
        detailsPanel.add(accountNumberField);
        detailsPanel.add(balanceLabel);
        detailsPanel.add(balanceField);
        detailsPanel.add(AccTypeLabel);
        detailsPanel.add(AccTypeField);

        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        fundstransferButton = new JButton("Account Transfer");
        switchAccountsButton = new JButton("Switch Accounts");
        writeButton = new JButton("Add Account");

        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        transferButton.setEnabled(false);
        fundstransferButton.setEnabled(false);
        switchAccountsButton.setEnabled(false);
        writeButton.setEnabled(true);

        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(transferButton);
        buttonsPanel.add(fundstransferButton);
        buttonsPanel.add(switchAccountsButton);
        buttonsPanel.add(writeButton);

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
                else {
                    String accNum = loginField.getText().trim();
                    if(searchAccount(accounts, accNum) == -1)
                        JOptionPane.showMessageDialog(null, "No accounts found for account number: " + accNum);
                    else {
                        owner = accounts.get(searchAccount(accounts, accNum));
                        holderNameField.setText(owner.getaccountName());
                        accountNumberField.setText(owner.getAccountNumber());
                        balanceField.setText(String.format("$ %.2f", owner.getBalance()));
                        if(owner instanceof Checking){
                            AccTypeField.setText("Checking"); }
                        else{
                            AccTypeField.setText("Savings"); }
                        depositButton.setEnabled(true);
                        withdrawButton.setEnabled(true);
                        transferButton.setEnabled(true);
                        fundstransferButton.setEnabled(true);
                        switchAccountsButton.setEnabled(true);
                    }
                }
            }
        });

// action listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = JOptionPane.showInputDialog("Deposit Amount:");
                while(!isDouble(inp)) {
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
                while(!isDouble(inp)) {
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
                if(accNum.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter transfer account number.");
                    return;
                }
                int index = searchAccount(accounts, accNum);
                if(index == -1) {
                    JOptionPane.showMessageDialog(null, "Account Number Does Not Have an Account: " + accNum);
                    return;
                }
                Account transfer = accounts.get(index);
                double inp = Double.parseDouble(JOptionPane.showInputDialog("Transfer Amount:"));
                owner.transfer(transfer, inp);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

        // action listener for account funds transfer button
        fundstransferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                for(int i = 0; i < accounts.size(); i++) {
                    if(accounts.get(i).getAccountNumber().equals(owner.getAccountNumber()) & owner instanceof Checking!=accounts.get(i) instanceof Checking) {
                        index = i;
                    }
                }
                if(index == -1) {
                    JOptionPane.showMessageDialog(null, "Account Number Does Not Have Another Account: " + owner.getAccountNumber());
                    fundstransferButton.setEnabled(false);
                    switchAccountsButton.setEnabled(false);
                    return;
                }
                double inp = Double.parseDouble(JOptionPane.showInputDialog("Transfer Amount:"));
                owner.withdraw(inp);
                accounts.get(index).deposit(inp);
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
            }
        });

// action listener for switch accounts button
        switchAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = -1;
                for(int i = 0; i < accounts.size(); i++) {
                    if(accounts.get(i).getAccountNumber().equals(owner.getAccountNumber()) & owner instanceof Checking!=accounts.get(i) instanceof Checking) {
                        index = i;
                    }
                }
                if(index == -1) {
                    JOptionPane.showMessageDialog(null, "Account Number Does Not Have Another Account: " + owner.getAccountNumber());
                    fundstransferButton.setEnabled(false);
                    switchAccountsButton.setEnabled(false);
                    return;
                }
                owner = accounts.get(index);
                holderNameField.setText(owner.getaccountName());
                accountNumberField.setText(owner.getAccountNumber());
                balanceField.setText(String.format("$ %.2f", owner.getBalance()));
                if(owner instanceof Checking){
                    AccTypeField.setText("Checking"); }
                else {
                    AccTypeField.setText("Savings"); }
            }
        });

// action listener for write button
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountname = JOptionPane.showInputDialog("Account Name");
                String accounttype = JOptionPane.showInputDialog("Account Type (Checking/Savings)");
                if(!accounttype.equals("Checking") & !accounttype.equals("Savings")){
                    JOptionPane.showMessageDialog(null, "Please Enter 'Checking' or 'Savings'");
                    return;
                }
                String accountnumber = JOptionPane.showInputDialog("Account Number");
                Boolean instancetype = null;
                if(accounttype.equals("Checking")){
                    instancetype = true;
                }
                if(accounttype.equals("Savings")){
                    instancetype = false;
                }
                if(searchAccount(accounts,accountnumber)!=-1){
                    if(accounts.get(searchAccount(accounts,accountnumber)) instanceof Checking==instancetype){
                        JOptionPane.showMessageDialog(null, "Account with same Account Number and Type already exists");
                        return;
                    }
                }
                double initialamount = Double.parseDouble(JOptionPane.showInputDialog("Initial Deposit Amount"));
                if(accounttype.equals("Checking")){
                    accounts.add(new Checking(accountname,accountnumber,initialamount));
                    JOptionPane.showMessageDialog(null, "Checking Account Created!");
                }
                if(accounttype.equals("Savings")){
                    accounts.add(new Savings(accountname,accountnumber,initialamount));
                    JOptionPane.showMessageDialog(null, "Savings Account Created!");
                }
            }
        });
    }

    private static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accs = new ArrayList<>();
        accs.add(new Checking("Grant", "1234", 5000));
        accs.add(new Savings("Grant", "1234", 10000));
        accs.add(new Checking("Miriam", "4321", 4000));
        accs.add(new Savings("Miriam", "4321", 8000));
        accs.add(new Savings("Dave", "6789", 7500));
        return accs;
    }

    private static int searchAccount(ArrayList<Account> accounts, String accountNumber) {
        int index = -1;
        for(int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getAccountNumber().equals(accountNumber)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, s + "Please Enter a Number");
            return false;
        }
    }
}
