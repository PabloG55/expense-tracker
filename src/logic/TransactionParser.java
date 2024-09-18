package logic;

import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {
    private final ArrayList<Transaction> transactions = new ArrayList<>();


    public TransactionParser(ArrayList<String> transactionLines) {
        Pattern pattern = Pattern.compile(
    "(\\d{1,2}/\\d{1,2}/\\d{4})\\t(\\d{1,2}/\\d{1,2}/\\d{4})\\t(.+?)\\t(-?\\d+(?:\\.\\d{1,2})?)\\t(.+)"
        );

        for (String line : transactionLines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String date = matcher.group(1);
                String postedDate = matcher.group(2);
                String description = matcher.group(3);
                double amount = Double.parseDouble(matcher.group(4));
                String category = matcher.group(5);

                Transaction transaction = new Transaction(date, postedDate, description, amount, category);
                transactions.add(transaction);
            }
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
