package logic;

import java.util.Scanner;

public class TransactionCategorizer {
    private final Scanner scanner = new Scanner(System.in);
    private final UndoRedoManager undoRedoManager = new UndoRedoManager();

    public Transaction categorize(Transaction transaction) {
        // Save the current state for undo
        undoRedoManager.saveState(transaction);

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
            transaction = splitTransaction(transaction);
        }

        return transaction;
    }

    private SplitTransaction splitTransaction(Transaction transaction) {
        System.out.print("Enter the amount you want to split as personal expense: ");
        double splitAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        while (splitAmount <= 0 || splitAmount >= transaction.getAmount()) {
            System.out.print("Invalid split amount. Please enter a value between 0 and " + transaction.getAmount() + ": ");
            splitAmount = scanner.nextDouble();
            scanner.nextLine(); // consume newline
        }

        return new SplitTransaction(transaction.date, transaction.postedDate, transaction.description,
                transaction.getAmount(), transaction.category, splitAmount);
    }

    public Transaction undo() {
        return undoRedoManager.undo();
    }

    public Transaction redo() {
        return undoRedoManager.redo();
    }
}