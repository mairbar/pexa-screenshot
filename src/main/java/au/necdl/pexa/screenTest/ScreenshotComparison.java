package au.necdl.pexa.screenTest;

import au.necdl.pexa.screenTest.image.ComparisonResult;
import au.necdl.pexa.screenTest.image.ComparisonResults;
import au.necdl.pexa.screenTest.image.DirectoryBasedImageComparison;
import au.necdl.pexa.screenTest.image.FileBasedImageComparison;

import java.io.IOException;

public class ScreenshotComparison {
    public static void main( String[] args ) throws Exception {
        String referenceDir = "reference-images";
        String candidateDir = "candidate-images";

//        FileBasedImageComparison fileBasedImageComparison = new FileBasedImageComparison("reference-images/pexa-1.png", "candidate-images/pexa-1.png", "candidate-images/pexa-diff-becs.png");
//        fileBasedImageComparison.compare();

        DirectoryBasedImageComparison dirBasedImageComparison = new DirectoryBasedImageComparison(referenceDir, candidateDir);
        ComparisonResults results = dirBasedImageComparison.compare();
        for (ComparisonResult result : results.getComparisonResults()) {
            if (result.isPassed()) {

            } else {

            }
        }
    }
}
