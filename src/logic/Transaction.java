package logic;

public class Transaction {
    private final String date;
    private final String postedDate;
    private final String description;
    private final double amount;
    private final String category;

    public Transaction(String date, String postedDate, String description, double amount, String category) {
        this.date = date;
        this.postedDate = postedDate;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Date: %s, Posted: %s, Description: %s, Amount: %.2f, Category: %s",
                date, postedDate, description, amount, category);
    }


}
