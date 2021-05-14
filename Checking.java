import javax.swing.JOptionPane;

public class Checking extends Account{


    private double overDraft;

    public Checking()
    {
        super();
        this.overDraft = 0;
    }

    public Checking(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, balance);
        this.overDraft = 1000;
    }

    @Override
    public void deposit(double amt) {
        setBalance(getBalance() + amt);
        JOptionPane.showMessageDialog(null, "Deposit of $" + String.format("%.2f", amt)
                + " is successful!");
        getTransactions().add("$" + String.format("%.2f", amt) + " is credited to account number " + getAccountNumber());
    }

    @Override
    public void withdraw(double amt) {
        if(getBalance() - amt < 0)
        {
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return;
        }

        if(getBalance() - amt < this.overDraft)
        {
            JOptionPane.showMessageDialog(null, "Exceeded overdraft limit Reached"
                    + "to your account");
            setBalance(getBalance() - amt - 25);
        }
        else
            setBalance(getBalance() - amt);

        JOptionPane.showMessageDialog(null, "Withdrawal of $" + String.format("%.2f", amt)
                + " is successful!");
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

        if(getBalance() - amt < this.overDraft)
        {
            JOptionPane.showMessageDialog(null, "Overdraft limit reached"
                    + "to your account");
            setBalance(getBalance() - amt - 25);
        }
        else
            setBalance(getBalance() - amt);

        acc.setBalance(acc.getBalance() + amt);
        JOptionPane.showMessageDialog(null, "Transfer Completed");
        getTransactions().add("$" + String.format("%.2f", amt)
                + " Money has been transferred from account " + getAccountNumber() + " to account " + acc.getAccountNumber());
    }
}
