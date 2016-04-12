package au.necdl.pexa.screenTest.image;

import java.io.File;
import static au.necdl.pexa.screenTest.utils.Utils.println;

/**
 * Created by Rebecca Abriam.
 */
public class DirectoryBasedImageComparison {
    private File referenceDir;
    private File candidateDir;

    public DirectoryBasedImageComparison(String referenceDir, String candidateDir) {
        setReferenceDir(new File(referenceDir));
        setCandidateDir(new File(candidateDir));
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

    public ComparisonResults compare() throws Exception {
        ComparisonResults results = new ComparisonResults();

        for (File referenceImage : referenceDir.listFiles()) {
            boolean passed = true;
            ComparisonResult result = new ComparisonResult();
            File candidateImage = getCandidateImage(referenceImage);
            result.setReferenceImage(referenceImage);

//            println("Reference Image path: " + referenceImage.getAbsolutePath());
//            println("Candidate Image path: " + candidateImage.getAbsolutePath());

            if (candidateImage.exists()) {
                result.setCandidateImage(candidateImage);
                File diffImage = createDiffImage(candidateImage);
                FileBasedImageComparison fileBasedImageComparison = new FileBasedImageComparison(referenceImage, candidateImage, diffImage);

                if(!fileBasedImageComparison.compare()) {
                    passed = false;
                    result.setReason("Image mismatch");
                    println(">>> Image mismatch!");
                    if (diffImage.exists()) {
                        result.setDiffImage(diffImage);
                        println(">>> Comparison Diff image generated: " + diffImage.getAbsolutePath());
                    } else {
                        fileBasedImageComparison.composeDiffImage(referenceImage, candidateImage, diffImage);
                        result.setDiffImage(diffImage);
                        println(">>> Composite Diff image generated: " + diffImage.getAbsolutePath());
                    }
                } else {
                    result.setReason("Passed");
                }
//                println("exists");
            } else {
                passed = false;
                result.setReason("Missing candidate file");
                println("\nMissing candidate file " + candidateImage.getAbsolutePath());
                println(">>> Unable to process.");

            }
            result.setPassed(passed);
            results.add(result);
        }
        return results;
    }

    private File getCandidateImage(File referenceImage) {
        return new File(this.getCandidateDir().getAbsoluteFile() + "/" + referenceImage.getName());
    }

    private File createDiffImage(File candidateImage) {
        return new File(candidateImage.getAbsolutePath().replaceAll(".png", "-diff.png"));
    }

}
