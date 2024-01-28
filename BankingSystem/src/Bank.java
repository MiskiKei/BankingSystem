import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        if (account != null) {
            if (!accounts.contains(account)) {
                accounts.add(account);
                System.out.println("Account added: " + account.getAccountHolder());
            } else {
                System.out.println("Account already exists.");
            }
        } else {
            System.out.println("Cannot add null account.");
        }
    }

    public void processInterest() {
        for (Account account : accounts) {
            account.calculateInterest();
        }
        System.out.println("Interest processed for all accounts.");
    }

    public void printAccountDetails() {
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("Account details printed.");
    }
    
    public Account checkingAccount(String accountHolder, double initialBalance) {
        if (accountHolder != null && !accountHolder.isEmpty() && initialBalance >= 0) {
            Account checkingAccount = new CheckingAccount(accountHolder, initialBalance, 1.0);
            addAccount(checkingAccount);
            return checkingAccount;
        } else {
            System.out.println("Invalid parameters for creating a checking account.");
            return null;
        }
    }

    public Account savingsAccount(String accountHolder, double initialBalance) {
        if (accountHolder != null && !accountHolder.isEmpty() && initialBalance >= 0) {
            Account savingsAccount = new SavingsAccount(accountHolder, initialBalance, 2.0, 100.0);
            addAccount(savingsAccount);
            return savingsAccount;
        } else {
            System.out.println("Invalid parameters for creating a savings account.");
            return null;
        }
    }
}
