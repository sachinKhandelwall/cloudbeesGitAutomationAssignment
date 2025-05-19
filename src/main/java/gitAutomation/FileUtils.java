package gitAutomation;

import java.io.*;

public class FileUtils {

    public static void writeNewFile(File repoDir, String fileName, String content) throws IOException {
        File file = new File(repoDir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
    }

    public static void appendToFile(File repoDir, String fileName, String content) throws IOException {
        File file = new File(repoDir, fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
        }
    }
}
