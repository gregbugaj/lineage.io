package io.lineage.test.validator;

import io.lineage.Chromosome;
import io.lineage.Decoder;
import io.lineage.FitnessSelector;
import io.lineage.GAExecutionContext;

/**
 * Validate according to following http://www.eis.mdx.ac.uk/staffpages/rvb/teaching/BIS3226/sol15.pdf
 */
public class ValidatorFitnessSelector implements FitnessSelector
{
    @Override
    public void evaluate(final Chromosome target)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Decoder<FunctionParams> decoder = context.getDecoder();
        final FunctionParams lhs = decoder.decode(target);
        final int x = (lhs.a + lhs.b) - (lhs.c + lhs.d) + (lhs.e + lhs.f) - (lhs.g + lhs.h);
        target.fitness = x;
    }
}
