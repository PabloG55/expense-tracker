package logic;

import java.util.Stack;

public class UndoRedoManager {
    private final Stack<Transaction> undoStack = new Stack<>();
    private final Stack<Transaction> redoStack = new Stack<>();

    public void saveState(Transaction transaction) {
        if (transaction instanceof SplitTransaction) {
            undoStack.push(new SplitTransaction((SplitTransaction) transaction));
        } else {
            undoStack.push(new Transaction(transaction));
        }
        redoStack.clear();
    }

    public Transaction undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return null;
        }

        Transaction lastTransaction = undoStack.pop();
        if (lastTransaction instanceof SplitTransaction) {
            redoStack.push(new SplitTransaction((SplitTransaction) lastTransaction));
        } else {
            redoStack.push(new Transaction(lastTransaction));
        }

        System.out.println("Undo completed.");
        return lastTransaction;
    }

    public Transaction redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return null;
        }

        Transaction nextTransaction = redoStack.pop();
        if (nextTransaction instanceof SplitTransaction) {
            undoStack.push(new SplitTransaction((SplitTransaction) nextTransaction));
        } else {
            undoStack.push(new Transaction(nextTransaction));
        }

        System.out.println("Redo completed.");
        return nextTransaction;
    }
}