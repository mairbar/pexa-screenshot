package au.necdl.pexa.screenTest.image;

import java.io.File;

/**
 * Created by Rebecca Abriam.
 */
public class ComparisonResult {
    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public File getReferenceImage() {
        return referenceImage;
    }

    public void setReferenceImage(File referenceImage) {
        this.referenceImage = referenceImage;
    }

    public File getCandidateImage() {
        return candidateImage;
    }

    public void setCandidateImage(File candidateImage) {
        this.candidateImage = candidateImage;
    }

    public File getDiffImage() {
        return diffImage;
    }

    public void setDiffImage(File diffImage) {
        this.diffImage = diffImage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String testUrl;
    private boolean passed;
    private File referenceImage;
    private File candidateImage;
    private File diffImage;
    private String reason;
}
