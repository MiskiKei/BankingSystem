public class Driver {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Account aliceChecking = new CheckingAccount("Alice", 1000.0, 1.0);  // 1.0% interest rate for checking account
        Account bobSavings = new SavingsAccount("Bob", 500.0, 2.0, 100.0);  // 2.0% interest rate for savings account

        bank.addBankAccount(aliceChecking);
        bank.addBankAccount(bobSavings);

        System.out.println("Initial account details:");
        bank.printAccountDetails();

        System.out.println();

        aliceChecking.logTransaction("Purchase", 150.0);
        bobSavings.logTransaction("Deposit", 150.0);  

        System.out.println();
      
        aliceChecking.transferMoney(bobSavings, 200.0);;

        System.out.println();

        bank.processInterestAmount();

        System.out.println();

        System.out.println("\nAccount details after transfer and interest:");
        bank.printAccountDetails();
    }
}
