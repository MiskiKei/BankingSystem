import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountHolder;
    protected double balance;
    protected double interestRate;
    protected List<Transaction> transactions;

    public Account(String accountHolder, double balance, double interestRate) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.interestRate = interestRate;
        this.transactions = new ArrayList<>();
    }

    public abstract double getOverdraftLimit();

    public String getAccountHolder() {
        if (accountHolder != null && !accountHolder.isEmpty()) {
            return accountHolder.trim();
        } else {
            return "Unknown Account Holder";
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            System.out.println("Deposit of $" + amount + " processed for " + getAccountHolder());
        } else {
            System.out.println("Invalid deposit amount for " + getAccountHolder());
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            if (balance + getOverdraftLimit() >= amount) { // Check if there are sufficient funds, including overdraft limit
                balance -= amount;
                transactions.add(new Transaction("Withdrawal", amount));
                System.out.println("Withdrawal of $" + amount + " processed for " + getAccountHolder());
                return true;
            } else {
                System.out.println("Withdrawal failed. Insufficient funds or exceeding overdraft limit for "
                        + getAccountHolder());
                return false;
            }
        } else {
            System.out.println("Invalid withdrawal amount for " + getAccountHolder());
            return false;
        }
    }

    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        deposit(interest);
        transactions.add(new Transaction("Interest", interest));
        System.out.println("Interest calculated and deposited for " + getAccountHolder());
    }
    
    public void logTransaction(String transactionType, double amount) { 
        transactions.add(new Transaction(transactionType, amount));
        System.out.println("Transaction logged: Type: " + transactionType + ", Amount: $" + amount);
    }

    // Override toString method to provide a string representation of the account
    @Override
    public String toString() {
        return "[Account] Account Holder: " + getAccountHolder() +
                ", Balance: $" + balance +
                ", Interest Rate: " + interestRate + "%" +
                ", Transactions: " + transactions;
    }
}
