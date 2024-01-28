public class SavingsAccount extends Account {
    private double minimumBalance;

    public SavingsAccount(String accountHolder, double balance, double interestRate, double minimumBalance) {
        super(accountHolder, balance, interestRate);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public double getOverdraftLimit() { 
        return 0.0;
    }

    @Override
    public boolean withdrawMoney(double amount) {
        if (balance - amount >= minimumBalance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal of $" + amount + " processed for " + getAccountHolder());
            return true;
        } else {
            System.out.println("Withdrawal failed. Minimum balance requirement not met for "
                    + getAccountHolder());
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Savings Account] " + super.toString() +
                ", Minimum Balance: $" + minimumBalance;
    }
}
