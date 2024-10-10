package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void exportExpensesToFile(String fileName, String content, String content2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.newLine();
            writer.write("---------------------------------------------------------------------------------------------------------------------");
            writer.newLine();
            writer.newLine();
            writer.write(content2);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}