package first;

import java.util.Scanner;

import javax.naming.InsufficientResourcesException;

public class BankingSystem {
    private double[] accountBalances;
    private int numAccounts;

    public BankingSystem(int numAccounts) {
        this.numAccounts = numAccounts;
        accountBalances = new double[numAccounts];
        // Initialize account balances to zero
        for (int i = 0; i < numAccounts; i++) {
            accountBalances[i] = 0.0;
        }
    }

    public void deposit(int accountNumber, double amount) {
        try {
            if (accountNumber < 0 || accountNumber >= numAccounts) {
                throw new IllegalArgumentException("Invalid account number");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid deposit amount");
            }
            accountBalances[accountNumber] += amount;
            System.out.println("Deposit successful. New balance: " + accountBalances[accountNumber]);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void withdraw(int accountNumber, double amount) {
        try {
            if (accountNumber < 0 || accountNumber >= numAccounts) {
                throw new IllegalArgumentException("Invalid account number");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid withdrawal amount");
            }
            if (amount > accountBalances[accountNumber]) {
                throw new InsufficientResourcesException("Insufficient funds");
            }
            accountBalances[accountNumber] -= amount;
            System.out.println("Withdrawal successful. New balance: " + accountBalances[accountNumber]);
        } catch (IllegalArgumentException | InsufficientResourcesException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem(5);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter account number:");
                int accountNumber = scanner.nextInt();
                System.out.println("Enter deposit amount:");
                double amount = scanner.nextDouble();
                bankingSystem.deposit(accountNumber, amount);
            } else if (choice == 2) {
                System.out.println("Enter account number:");
                int accountNumber = scanner.nextInt();
                System.out.println("Enter withdrawal amount:");
                double amount = scanner.nextDouble();
                bankingSystem.withdraw(accountNumber, amount);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}