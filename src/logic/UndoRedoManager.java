package logic;

import java.util.Stack;

public class UndoRedoManager {
    private final Stack<Transaction> undoStack = new Stack<>();
    private final Stack<Transaction> redoStack = new Stack<>();

    public void saveState(Transaction transaction) {
        undoStack.push(new Transaction(transaction));
        redoStack.clear();
    }

    public Transaction undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return null;
        }

        Transaction lastTransaction = undoStack.pop();
        redoStack.push(new Transaction(lastTransaction));

        if (lastTransaction.getType() == null) {
            System.out.println("Reached original state. Nothing more to undo.");
            return lastTransaction;
        }

        if ("Split".equals(lastTransaction.getType())) {
            Transaction originalTransaction = new Transaction(lastTransaction);
            originalTransaction.setType(null);
            originalTransaction.setSplitAmount(0);
            System.out.println("Undo completed (Split transaction).");
            return originalTransaction;
        }

        System.out.println("Undo completed.");
        return new Transaction(lastTransaction);
    }

    public Transaction redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return null;
        }

        Transaction nextTransaction = redoStack.pop();
        undoStack.push(new Transaction(nextTransaction));

        if (nextTransaction.getType() != null && nextTransaction.getType().equals("Split")) {
            System.out.println("Redo completed (Split transaction).");
        } else {
            System.out.println("Redo completed.");
        }

        return new Transaction(nextTransaction);
    }
}