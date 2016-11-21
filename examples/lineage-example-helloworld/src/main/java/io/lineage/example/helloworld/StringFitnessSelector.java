package io.lineage.example.helloworld;

import io.lineage.Chromosome;
import io.lineage.Decoder;
import io.lineage.FitnessSelector;
import io.lineage.GAExecutionContext;
import io.lineage.Population;

public class StringFitnessSelector implements FitnessSelector
{
    private final ISimilarityCalculator calc = new LevenshteinSimilarity();

    
    public void evaluate(final Population solutions, final Chromosome target)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Decoder<String> decoder = context.getDecoder();
        final String rhs = decoder.decode(target);

        double score = 0;
        for (final Chromosome solution : solutions)
        {
            final String lhs = decoder.decode(solution);
            final double v = calc.calculate(lhs,rhs);
            final double d = Math.min(v,1);
            score += d;
        }

        target.fitness = score / solutions.size();
    }

    @Override
    public void evaluate(final Chromosome target)
    {
            throw new RuntimeException("Not Implemented");
         
    }
}
