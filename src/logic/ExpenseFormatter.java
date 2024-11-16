package logic;

import java.util.ArrayList;

public class ExpenseFormatter {
    private final StringBuilder personalExpenses;
    private final StringBuilder generalExpenses;

    public ExpenseFormatter() {
        personalExpenses = new StringBuilder("Personal Expenses:\n");
        generalExpenses = new StringBuilder("General Expenses:\n");
    }

    public void formatExpenses(ArrayList<Transaction> transactions, ExpenseCalculator expenseCalculator) {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                continue;
            }

            if ("Personal".equalsIgnoreCase(transaction.getType())) {
                personalExpenses.append(transaction).append("\n");
            } else if ("General".equalsIgnoreCase(transaction.getType())) {
                generalExpenses.append(transaction).append("\n");
            } else if ("Split".equalsIgnoreCase(transaction.getType())) {
                // Handle split transactions
                Transaction generalPart = new Transaction(transaction);
                generalPart.setAmount(transaction.getAmount() - transaction.getSplitAmount());
                generalExpenses.append(generalPart).append("\n");

                Transaction personalPart = new Transaction(transaction);
                personalPart.setAmount(transaction.getSplitAmount());
                personalExpenses.append(personalPart).append("\n");
            }
        }

        personalExpenses.append("\nTotal Personal Expenses: ").append(String.format("%.2f", expenseCalculator.getPersonalTotal())).append("\n");
        generalExpenses.append("\nTotal General Expenses: ").append(String.format("%.2f", expenseCalculator.getGeneralTotal())).append("\n");
    }

    public String getPersonalExpenses() {
        return personalExpenses.toString();
    }

    public String getGeneralExpenses() {
        return generalExpenses.toString();
    }
}