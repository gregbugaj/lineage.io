package io.lineage.example.helloworld;

import org.apache.commons.lang.StringUtils;

public class LevenshteinSimilarity implements ISimilarityCalculator
{
    @Override
    public double calculate(final String lhs, final String rhs)
    {
        final String one = StringUtils.trim(lhs);
        final String two = StringUtils.trim(rhs);

        if ((StringUtils.isBlank(one)) || (StringUtils.isBlank(two)))
            return 0.0;

        final int max = Math.max(one.length(), two.length());

        return Math.min(1.0, Math.max(0.0, 1.0 - ((double) StringUtils.getLevenshteinDistance(one, two)) / max));
    }
}
