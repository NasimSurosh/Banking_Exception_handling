package second;

import java.util.Scanner;

class BankAccount {
    private int accountNumber;
    private int pin;
    private double balance;

    public BankAccount(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }
        balance -= amount;
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class BankingSystem {
    private static BankAccount[] accounts = {
        new BankAccount(12345, 54321, 1000.0),
        new BankAccount(98765, 56789, 500.0)
    };

    public static BankAccount findAccount(int accountNumber, int pin) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin() == pin) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();

            BankAccount account = findAccount(accountNumber, pin);

            if (account == null) {
                System.out.println("Invalid account number or PIN.");
                continue;
            }

            System.out.println("Welcome, " + account.getAccountNumber() + "!");
            while (true) {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Balance: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        System.out.println("Deposited $" + depositAmount + " successfully.");
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawalAmount);
                            System.out.println("Withdrawn $" + withdrawalAmount + " successfully.");
                        } catch (InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Logging out.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

                if (choice == 4) {
                    break;
                }
            }
        }
    }
}
