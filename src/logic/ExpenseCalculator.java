package logic;

import java.util.ArrayList;

public class ExpenseCalculator {

    private double personalTotal;
    private double generalTotal;

    public ExpenseCalculator() {
        this.personalTotal = 0.0;
        this.generalTotal = 0.0;
    }


    public void calculateTotals(ArrayList<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                continue;
            }

            if ("Personal".equalsIgnoreCase(transaction.getType())) {
                personalTotal += transaction.getAmount();
            } else if ("General".equalsIgnoreCase(transaction.getType())) {
                generalTotal += transaction.getAmount();
            } else if ("Split".equalsIgnoreCase(transaction.getType())) {
                generalTotal += transaction.getAmount();
                personalTotal += transaction.getSplitAmount();
            }
        }
    }

    public double getPersonalTotal() {
        return personalTotal;
    }

    public double getGeneralTotal() {
        return generalTotal;
    }
}
