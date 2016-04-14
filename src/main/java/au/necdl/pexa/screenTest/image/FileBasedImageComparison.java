package au.necdl.pexa.screenTest.image;

import java.io.*;
import static au.necdl.pexa.screenTest.utils.Utils.println;

/**
 * Created by Rebecca Abriam.
 */
public class FileBasedImageComparison {
    private static final String FUZZ_PERCENTAGE = "5%";
    private File referenceFile;
    private File candidateFile;
    private File diffImage;

//    private String referenceImageName;
//    private String candidateImageName;
//    private String diffImageName;

    public FileBasedImageComparison() {

    }

    public FileBasedImageComparison(File referenceFile, File candidateFile, File diffImage) {
        this.referenceFile = referenceFile;
        this.candidateFile = candidateFile;
        this.diffImage = diffImage;
    }

//    public FileBasedImageComparison(String referenceImageName, String candidateImageName, String diffImageName) {
//        this.referenceImageName = referenceImageName;
//        this.candidateImageName = candidateImageName;
//        this.diffImageName = diffImageName;
//    }

//    private String getCompareArgsUsingFile() {
//        return getCompareArgsUsingFile(referenceFile, candidateFile, diffImage);
//    }

    private String getCompareArgsUsingFile(File referenceFile, File candidateFile, File diffImage) {
        StringBuilder compareArgs = new StringBuilder("-metric AE -fuzz ");
        compareArgs.append(FUZZ_PERCENTAGE);
        compareArgs.append(" ");
        compareArgs.append(escapeWhitespace(referenceFile.getAbsolutePath()));
        compareArgs.append(" ");
        compareArgs.append(escapeWhitespace(candidateFile.getAbsolutePath()));
        compareArgs.append(" ");
        compareArgs.append(escapeWhitespace(diffImage.getAbsolutePath()));
        return compareArgs.toString();
    }

//    private String getCompareArgs() {
//        StringBuilder compareArgs = new StringBuilder("-metric AE -fuzz ");
//        compareArgs.append(FUZZ_PERCENTAGE);
//        compareArgs.append(" ");
//        compareArgs.append(referenceImageName);
//        compareArgs.append(" ");
//        compareArgs.append(candidateImageName);
//        compareArgs.append(" ");
//        compareArgs.append(diffImageName);
//        return compareArgs.toString();
//    }

    public boolean compare() throws IOException, InterruptedException {
        return compare(referenceFile, candidateFile, diffImage);
    }

    public boolean compare(File referenceFile, File candidateFile, File diffImage) throws IOException, InterruptedException {
        //checkImageMagicInstalled();
        String commandStr = "compare " + getCompareArgsUsingFile(referenceFile, candidateFile, diffImage);

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", commandStr);

//        System.out.println("Comparing using compare args: " + getCompareArgsUsingFile());
        println("\nCompare reference and candidate images: " + referenceFile.getName());
        println(commandStr);

        Process p = pb.start();
        int errCode = p.waitFor();
        String output = getOutput(p.getErrorStream());

        boolean isTheSameImage = false;

        if (errCode == 0) { //no errors
            isTheSameImage = output.trim().compareTo("0") == 0;
            if (!isTheSameImage) {
                System.out.println("There were " + output + " pixels that didn't match (taking "+FUZZ_PERCENTAGE+" of fuzzy errors into consideration)");
            } else {
                System.out.println(">>> Reference and candidate image matches!");
            }
        } else { //with errors
            System.out.println("Imagemagick command failed with exit code: " + errCode);
            System.out.println("Failure reason: " + output);
        }
        return isTheSameImage;
    }

    public void composeDiffImage(File referenceImage, File candidateImage, File diffImage) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "composite " + referenceImage.getAbsolutePath() + " " + candidateImage.getAbsolutePath() + " -compose difference " + diffImage.getAbsolutePath());
        executeCommand(pb);
    }

    private boolean executeCommand(ProcessBuilder pb) throws Exception {
        Process p = pb.start();
        int errCode = p.waitFor();
        String output = getOutput(p.getErrorStream());
        if (errCode == 0) {
            return true;
        }
        return false;
    }

    private static String getOutput(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }


    private static String escapeWhitespace(String string) {
        return string.replaceAll(" ", "\\\\ ");
    }
}
