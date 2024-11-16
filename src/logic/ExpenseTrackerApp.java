package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTrackerApp {
    private final ArrayList<Transaction> transactions;

    public ExpenseTrackerApp() {
        transactions = new ArrayList<>();
    }

    public void loadTransactionsFromFile(String filePath) {
        FileOpener fileOpener = new FileOpener(filePath);
        ArrayList<String> transactionLines = fileOpener.getTransactions();
        TransactionParser transactionParser = new TransactionParser(transactionLines);
        transactions.addAll(transactionParser.getTransactions());
    }

    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        TransactionCategorizer categorizer = new TransactionCategorizer();

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() < 0) {
                continue;
            }

            categorizer.categorize(transaction);

            // Allow undo and redo actions during categorization
            while (true) {
                System.out.print("Do you want to undo, redo, or continue? (u/r/c): ");
                String choice = new Scanner(System.in).nextLine();

                if (choice.equalsIgnoreCase("u")) {
                    Transaction undoneTransaction = categorizer.undo();
                    if (undoneTransaction != null) {
                        transactions.set(i, undoneTransaction);
                        if (undoneTransaction.getType() == null) {
                            i--;
                            break;
                        }
                    }
                } else if (choice.equalsIgnoreCase("r")) {
                    Transaction redoneTransaction = categorizer.redo();
                    if (redoneTransaction != null) {
                        transactions.set(i, redoneTransaction);
                    }
                } else if (choice.equalsIgnoreCase("c")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'u', 'r', or 'c'.");
                }
            }
        }

        System.out.println("Finished displaying transactions.");
    }


    public void exportExpensesToFile() {
        // Create ExpenseCalculator and calculate totals
        ExpenseCalculator expenseCalculator = new ExpenseCalculator();
        expenseCalculator.calculateTotals(transactions);

        // Format the expenses using ExpenseFormatter
        ExpenseFormatter expenseFormatter = new ExpenseFormatter();
        expenseFormatter.formatExpenses(transactions, expenseCalculator);

        // Export the formatted expenses using FileExporter
        FileExporter fileExporter = new FileExporter();
        fileExporter.exportExpensesToFile("Expenses.txt", expenseFormatter.getGeneralExpenses(), expenseFormatter.getPersonalExpenses());

        System.out.println("Expenses exported to Expenses.txt");
    }

    private void writeToFile(String fileName, String content, String content2) {
        FileExporter fileExporter = new FileExporter();
        fileExporter.exportExpensesToFile(fileName, content, content2);
    }

    public void run() {
        loadTransactionsFromFile("C:\\Users\\pgarc\\OneDrive\\Documents\\UNCC\\2 semester\\Dsgn Sys\\expense-tracker\\src\\logic\\file.txt");

        // Display loaded transactions one by one and categorize them
        displayTransactions();

        // Export categorized transactions to files
        exportExpensesToFile();
    }

    public static void main(String[] args) {
        ExpenseTrackerApp app = new ExpenseTrackerApp();
        app.run();
    }
}


