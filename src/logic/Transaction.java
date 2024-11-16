package logic;

public class Transaction {
    private final String date;
    private final String postedDate;
    private final String description;
    private double amount;
    private final String category;
    private String type;
    private double splitAmount;

    // Constructor
    public Transaction(String date, String postedDate, String description, double amount, String category, String type, double splitAmount) {
        this.date = date;
        this.postedDate = postedDate;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.splitAmount = amount;
    }

    public Transaction(Transaction other) {
        this.date = other.date;
        this.postedDate = other.postedDate;
        this.description = other.description;
        this.amount = other.amount;
        this.category = other.category;
        this.type = other.type;
        this.splitAmount = other.splitAmount;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("Date: %s, Posted: %s, Description: %s, Amount: %.2f, Category: %s",
                date, postedDate, description, amount, category);
    }

    // Setter for type
    public void setType(String type) {
        this.type = type;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }

    public void setSplitAmount(double splitAmount) {
        this.splitAmount = splitAmount;
    }

    public double getSplitAmount() {
        return splitAmount;
    }

}
