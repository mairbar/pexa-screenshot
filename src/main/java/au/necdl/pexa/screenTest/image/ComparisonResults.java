package au.necdl.pexa.screenTest.image;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Created by Rebecca Abriam
 */
public class ComparisonResults {
    List<ComparisonResult> comparisonResults = new ArrayList<ComparisonResult>();

    public List<ComparisonResult> getPassedResults() {
        List<ComparisonResult> passedResults = Lists.newArrayList();
        for (ComparisonResult c : comparisonResults) {
            if (c.isPassed()) {
                passedResults.add(c);
            }
        }
        return passedResults;
    }

    public void add(ComparisonResult result) {
        comparisonResults.add(result);
    }

    public List<ComparisonResult> getComparisonResults() {
        return comparisonResults;
    }
}
