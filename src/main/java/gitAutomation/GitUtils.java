package gitAutomation;

import java.io.*;

public class GitUtils {

    public static void runGitCommand(String[] gitCommand, File workingDir) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(gitCommand);
        if (workingDir != null) {
            builder.directory(workingDir);
        }

        builder.redirectErrorStream(true);
        Process process = builder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Git repo clone operation failed: " + String.join(" ", gitCommand));
        }

    }

        public static void gitClone(String repoUrl, File destDir) throws IOException, InterruptedException {
            String[] gitCloneCommand = {"git", "clone", repoUrl, destDir.getAbsolutePath()};
            runGitCommand(gitCloneCommand, null);
        }

        public static void gitAddAndCommit(String commitMessage, File repoDir) throws IOException, InterruptedException {
            String[] gitAddCommand = {"git", "add", "."};
            String[] gitCommitCommand = {"git", "commit", "-m", commitMessage};
            runGitCommand(gitAddCommand, repoDir);
            runGitCommand(gitCommitCommand, repoDir);
        }
    }

