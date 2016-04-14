package au.necdl.pexa.screenTest.image;

import java.util.*;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import static com.google.common.collect.Collections2.filter;

/**
 * Created by Rebecca Abriam
 */
public class ComparisonResults {
    private List<ComparisonResult> comparisonResults = new ArrayList<ComparisonResult>();

    private long startTime = 0L;
    private long endTime = 0L;

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getPassedCount() {
        List<ComparisonResult> passedResults = new ArrayList<ComparisonResult>();
        for (ComparisonResult result : comparisonResults) {
            if (result.isPassed()) {
                passedResults.add(result);
            }
        }
        return passedResults.size();
    }

    public int getTotalCount() {
        return getComparisonResults().size();
    }
}
