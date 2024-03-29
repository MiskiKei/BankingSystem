public class CheckingAccount extends Account {
    public CheckingAccount(String accountHolder, double balance, double interestRate) {
        super(accountHolder, balance, interestRate);
    }

    @Override
    public double getOverdraftLimit() { 
        return 500.0;
    }

    @Override
    public String toString() {
        return "[Checking Account] " + super.toString();
    }
}
