//Savings.java
import javax.swing.JOptionPane;

public class Savings extends Account{

    public Savings(){ super(); }

    public Savings(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, balance);
    }

    @Override
    public void deposit(double amt) {
        setBalance(getBalance() + amt);
        JOptionPane.showMessageDialog(null, "Deposit of $" + String.format("%.2f", amt)
                + "successful");
        getTransactions().add("$" + String.format("%.2f", amt) + " is credited to account number " + getAccountNumber());
    }

    @Override
    public void withdraw(double amt) {
        if(getBalance() - amt < 0)
        {
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return;
        }
        setBalance(getBalance() - amt);
        JOptionPane.showMessageDialog(null, "Withdrawal of $" + String.format("%.2f", amt)
                + "successful");
        getTransactions().add("$" + String.format("%.2f", amt) + " is debited from account number " + getAccountNumber());
    }

    @Override
    public void transfer(Account acc, double amt) {
        if(getAccountNumber().equals(acc.getAccountNumber()))
        {
            JOptionPane.showMessageDialog(null, "Funds can't be transferred");
            return;
        }

        if(getBalance() - amt < 0)
        {
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return;
        }
        setBalance(getBalance() - amt);
        acc.setBalance(acc.getBalance() + amt);
        JOptionPane.showMessageDialog(null, "Transfer successful");
        getTransactions().add("$" + String.format("%.2f", amt)
                + " has been transferred from account " + getAccountNumber() + " to account" + acc.getAccountNumber());
    }

}
