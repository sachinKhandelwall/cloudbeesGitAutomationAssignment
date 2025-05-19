package gitAutomation;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GitAutomationTest {

    private static final String GIT_REPO_URL = "https://github.com/sachinKhandelwall/cloudbeesGitAutomationAssignment.git"; // Replace with actual git repo url
    private static final String clonedGitRepoPath = System.getProperty("user.dir") + File.separator + "clonedGitRepo"; //
    private File repoDirectory;
    private static final String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date());
    private static final String newFileName = "newFile_" + timestamp + ".txt";
    private static final String newFileContent = "This is new content";
    private static final String newFileAddedCommitMessage = "added new file: " + newFileName;
    private static final String existingFileName = "README.md";
    private static final String appendContentInExistingFile = "\nAppended content_" + timestamp;
    private static final String updatedFileCommitMessage = "updated file: " + newFileName;

    @BeforeTest
    @Story("Clone a git repo")
    @Description("Clones a git repo on a local directory if not cloned already")
    public void cloneGitRepo() throws Exception{
        repoDirectory = new File(clonedGitRepoPath);
        if (!repoDirectory.exists()) {
            Allure.step("Cloning the Git Repository in a directory");
            GitUtils.gitClone(GIT_REPO_URL, repoDirectory);
        } else {
            Allure.step("Directory already exists. Skipping clone.");
            System.out.println("Directory already exists. Skipping clone.");
        }
    }

    @Test
    @Story("Add new file to the cloned git repo")
    @Description("Adds a new file with some content")
    public void addNewFileInClonedGitRepo() throws Exception {
        Allure.step("Creating a new file in the cloned git repo directory with some content");
        FileUtils.writeNewFile(repoDirectory, newFileName, newFileContent);

        Allure.step("Adding and committing the new file");
        GitUtils.gitAddAndCommit(newFileAddedCommitMessage, repoDirectory);
    }

    @Test
    @Story("Update existing file in the cloned git repo")
    @Description("Appends some content to an existing file")
    public void updateExistingFileInClonedGitRepo() throws Exception {
        Allure.step("Appending some contents to an existing file of the cloned git repo");
        FileUtils.appendToFile(repoDirectory, existingFileName, appendContentInExistingFile);

        Allure.step("Adding and committing the updated file");
        GitUtils.gitAddAndCommit(updatedFileCommitMessage, repoDirectory);
    }
}
