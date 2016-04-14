package au.necdl.pexa.screenTest.image;

import com.google.common.io.Files;

import java.io.File;
import static au.necdl.pexa.screenTest.utils.Utils.println;

/**
 * Created by Rebecca Abriam.
 */
public class DirectoryBasedImageComparison {
    private File referenceDir;
    private File candidateDir;
    private ComparisonResults results;

    public DirectoryBasedImageComparison(String referenceDir, String candidateDir) {
        setReferenceDir(new File(referenceDir));
        setCandidateDir(new File(candidateDir));
    }

    public ComparisonResults compare() throws Exception {
        results = new ComparisonResults();
        compareDirectory(referenceDir);
        return results;
    }

    private void compareDirectory(File referenceDirectory) throws Exception {
        File[] files = referenceDirectory.listFiles();

        if (files != null && files.length > 0) {
            for (File file : referenceDirectory.listFiles()) {
                if (file.isDirectory()) {
                    compareDirectory(file);
                } else {
                    if (!Files.getFileExtension(file.getAbsolutePath()).equals("png")) {
                        continue;
                    }
                    boolean passed = true;
                    ComparisonResult result = new ComparisonResult();
                    File candidateImage = getCandidateImage(file);
                    result.setReferenceImage(file);

                    if (candidateImage.exists()) {
                        result.setCandidateImage(candidateImage);
                        File diffImage = createDiffImage(candidateImage);
                        FileBasedImageComparison fileBasedImageComparison = new FileBasedImageComparison(file, candidateImage, diffImage);

                        if(!fileBasedImageComparison.compare()) {
                            passed = false;
                            result.setReason("Image mismatch");
                            println(">>> Image mismatch!");
                            if (diffImage.exists()) {
                                result.setDiffImage(diffImage);
                                println(">>> Comparison Diff image generated: " + diffImage.getAbsolutePath());
                            } else {
                                fileBasedImageComparison.composeDiffImage(file, candidateImage, diffImage);
                                result.setDiffImage(diffImage);
                                println(">>> Composite Diff image generated: " + diffImage.getAbsolutePath());
                            }
                        } else {
                            result.setReason("Passed");
                        }
                    } else {
                        passed = false;
                        result.setReason("Missing candidate file");
                        println("\nMissing candidate file " + candidateImage.getAbsolutePath());
                        println(">>> Unable to process.");

                    }
                    result.setPassed(passed);
                    results.add(result);
                }
            }
        }
    }

    private File getCandidateImage(File referenceImage) {
        return new File(referenceImage.getAbsolutePath().replaceAll("\\\\", "/").replaceAll(this.referenceDir.getAbsolutePath().replaceAll("\\\\", "/"), this.candidateDir.getAbsolutePath().replaceAll("\\\\", "/")));
    }

    private File createDiffImage(File candidateImage) {
        return new File(candidateImage.getAbsolutePath().replaceAll(".png", "-diff.png"));
    }

    public File getReferenceDir() {
        return referenceDir;
    }

    public void setReferenceDir(File referenceDir) {
        this.referenceDir = referenceDir;
    }

    public File getCandidateDir() {
        return candidateDir;
    }

    public void setCandidateDir(File candidateDir) {
        this.candidateDir = candidateDir;
    }

}
