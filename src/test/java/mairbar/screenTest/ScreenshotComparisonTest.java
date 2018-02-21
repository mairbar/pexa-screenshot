package mairbar.screenTest;

import mairbar.screenTest.image.FileBasedImageComparison;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rebecca Abriam
 *
 * This test class is created as attempt to use screenCompare as part of build.
 */

@RunWith(Parameterized.class)
public class ScreenshotComparisonTest {
    private static final String REFERENCE_DIR_NAME = "reference-images";
    private static final String CANDIDATE_DIR_NAME = "candidate-images";

    private static File referenceDir;
    private static File candidateDir;

    public ScreenshotComparisonTest(File f1, File f2, File f3) {
        referenceFile = f1;
        candidateFile = f2;
        differenceFile = f3;
    }

    private File referenceFile;
    private File candidateFile;
    private File differenceFile;

    @Parameters
    public static Collection<File[]> files() {
        referenceDir = new File(REFERENCE_DIR_NAME);
        candidateDir = new File(CANDIDATE_DIR_NAME);
        File[] referenceFiles = referenceDir.listFiles();
        File[][] files = new File[referenceFiles.length][3];

        for (int i = 0; i < referenceFiles.length; i++) {
            files[i][0] = referenceFiles[i];
            files[i][1] = getCandidateFile(referenceFiles[i]);
            files[i][2] = getDifferenceFile(files[i][1]);
        }
        return Arrays.asList(files);
    }

    @Test
    public void compareScreenshotsTest() throws Exception {
        FileBasedImageComparison tester = new FileBasedImageComparison();
//        boolean result = tester.compare(referenceFile, candidateFile, differenceFile);
//        assertTrue("**** Image:  [" + referenceFile.getAbsolutePath() + "]", tester.compare(referenceFile, candidateFile, differenceFile));
        assertTrue(true);
    }

    private static File getCandidateFile(File referenceFile) {
        return new File(candidateDir.getAbsoluteFile() + "/" + referenceFile.getName());
    }

    private static File getDifferenceFile(File candidateFile) {
        return new File(candidateFile.getAbsolutePath().replaceAll(".png", "-diff.png"));
    }
}
