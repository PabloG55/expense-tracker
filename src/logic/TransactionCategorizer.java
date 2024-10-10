package logic;

import java.util.Scanner;

public class TransactionCategorizer {
    private final Scanner scanner = new Scanner(System.in);

    public void categorize(Transaction transaction) {
        System.out.println(transaction);
        System.out.print("Is this a personal or general expense?\nOr split?\n(Enter 'p', 'g', or 's'): ");
        String type = scanner.nextLine();

        while (!type.equalsIgnoreCase("p") && !type.equalsIgnoreCase("g") && !type.equalsIgnoreCase("s")) {
            System.out.print("Invalid input. Please enter 'p', 'g', or 's': ");
            type = scanner.nextLine();
        }

        if (type.equalsIgnoreCase("p")) {
            transaction.setType("Personal");
        } else if (type.equalsIgnoreCase("g")) {
            transaction.setType("General");
        } else {
            splitTransaction(transaction);
        }
    }

    private void splitTransaction(Transaction transaction) {
        System.out.print("(Enter the amount you want to split): ");
        double splitAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        double amount = transaction.getAmount() - splitAmount;
        transaction.setAmount(amount);
        transaction.setSplitAmount(splitAmount);
        transaction.setType("Split");
    }
}
