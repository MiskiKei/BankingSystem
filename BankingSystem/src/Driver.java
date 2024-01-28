public class Driver {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account aliceChecking = new CheckingAccount("Alice", 1000.0, 1.0);  // 1.0% interest rate for checking account
        Account bobSavings = new SavingsAccount("Bob", 500.0, 2.0, 100.0);  // 2.0% interest rate for savings account

        // Add accounts to the bank
        bank.addAccount(aliceChecking);
        bank.addAccount(bobSavings);

        System.out.println("Initial account details:");
        bank.printAccountDetails();

        System.out.println();

        aliceChecking.logTransaction("Purchase", 150.0);
        bobSavings.logTransaction("Deposit", 150.0);  // Log a transaction for Bob as well

        System.out.println();

        // Transfer money between accounts
        bank.transferMoney(aliceChecking, bobSavings, 200.0);
        aliceChecking.logTransaction("Transfer Out", 200.0);
        bobSavings.logTransaction("Transfer In", 200.0);

        System.out.println();

        bank.processInterest();

        System.out.println();

        System.out.println("\nAccount details after transfer and interest:");
        bank.printAccountDetails();
    }
}
