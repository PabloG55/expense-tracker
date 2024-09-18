import logic.FileOpener;
import logic.Transaction;
import logic.TransactionParser;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        FileOpener fileOpener = new FileOpener("C:\\Users\\pgarc\\OneDrive\\Documents\\UNCC\\2 semester\\Dsgn Sys\\expense-tracker\\src\\logic\\file.txt");

        ArrayList<String> transactionsLines = fileOpener.getTransactions();

        TransactionParser transactionParser = new TransactionParser(transactionsLines);
        ArrayList<Transaction> transactions = transactionParser.getTransactions();

        transactions.forEach(System.out::println);
    }
}