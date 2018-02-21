package mairbar.screenTest;

import mairbar.screenTest.image.ComparisonResults;
import mairbar.screenTest.image.DirectoryBasedImageComparison;

public class ScreenshotComparison {
    public static void main( String[] args ) throws Exception {
        String referenceDir = "reference-images";
        String candidateDir = "candidate-images";

//        FileBasedImageComparison fileBasedImageComparison = new FileBasedImageComparison("reference-images/test-1.png", "candidate-images/test-1.png", "candidate-images/test-diff-becs.png");
//        fileBasedImageComparison.compare();

        DirectoryBasedImageComparison dirBasedImageComparison = new DirectoryBasedImageComparison(referenceDir, candidateDir);
        ComparisonResults results = dirBasedImageComparison.compare();
//        for (ComparisonResult result : results.getComparisonResults()) {
//            if (result.isPassed()) {
//
//            } else {
//
//            }
//        }
    }
}
