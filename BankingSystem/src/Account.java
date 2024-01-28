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

    public void depositMoney(double amount) {
        if (amount > 0) {
            performDepositTransaction(amount);
            System.out.println("Deposit of $" + amount + " processed for " + getAccountHolder());
        } else {
            System.out.println("Invalid deposit amount for " + getAccountHolder());
        }
    }

	private void performDepositTransaction(double amount) {
		balance += amount;
		transactions.add(new Transaction("Deposit", amount));
	}

    public boolean withdrawMoney(double amount) {
        if (amount > 0) {
            if (balance + getOverdraftLimit() >= amount) { 
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
        depositMoney(balance * (interestRate / 100));
        transactions.add(new Transaction("Interest", balance * (interestRate / 100)));
        System.out.println("Interest calculated and deposited for " + getAccountHolder());
    }
    
    public void logTransaction(String transactionType, double amount) { 
        transactions.add(new Transaction(transactionType, amount));
        System.out.println("Transaction logged - Type: " + transactionType + ", Amount: $" + amount);
    }

    @Override
    public String toString() {
        return "Account Holder: " + getAccountHolder() +
                ", Balance: $" + balance +
                ", Interest Rate: " + interestRate + "%" +
                ", Transactions: " + transactions;
    }

	public void transferMoney(Account targetAccount, double amount) {
		if (targetAccount != null && amount > 0) {
	        if (withdrawMoney(amount)) {
	            targetAccount.depositMoney(amount);
	            System.out.println("Transfer successful: $" + amount + " transferred from "
	                    + getAccountHolder() + " to " + targetAccount.getAccountHolder());
	        } else {
	            System.out.println("Transfer failed. Insufficient funds in the source account.");
	        }
	    } else {
	        System.out.println("Invalid parameters for transfer.");
	    }
	}
}

