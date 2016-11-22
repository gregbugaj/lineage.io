package io.lineage;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation of {@link CrossoverOperator}. This implementation uses splicing method to create two new offsprings, example chromosome :
 * 
 * <pre>
 *  AAAAAAAAA
 *  BBBBBBBBB
 * </pre>
 * 
 * <p>
 * Single point cross over at position [6] will create :
 * </p>
 * 
 * <pre>
 *  AAAAAABBB
 *  BBBBBBAAA
 * </pre>
 * 
 * <p>
 * Two point cross over at positions [2, 8] will create :
 * </p>
 * 
 * <pre>
 *  AABBBBBBA
 *  BBAAAAAAB
 * </pre>
 */
public class SplicingCrossoverOperator implements CrossoverOperator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SplicingCrossoverOperator.class);

    private final Random rand = new SecureRandom();
    private final boolean twoPointCrossover = false;

    @Override
    public Chromosome crossover(final Chromosome parent1, final Chromosome parent2)
    {
        final List<Gene<?>> lhs = parent1.getGenes();
        final List<Gene<?>> rhs = parent2.getGenes();

        final int min = Math.min(lhs.size(),rhs.size());
        int cp1 = rand.nextInt(min);
        int cp2 = min;

        if (twoPointCrossover)
        {
            do
            {
                cp2 = rand.nextInt(min);
            }
            while (cp1 == cp2);

            // swap is faster than doing this in the 'while' loop.
            if (cp2 < cp1)
            {
                final int tmp = cp1;
                cp1 = cp2;
                cp2 = tmp;
            }
        }

        LOGGER.debug("CrossoverPoints twopoints = : {}  [{}, {}], size : {} ",new Object[]

        { twoPointCrossover, cp1, cp2, min });

        for (int i = cp1, j = cp2; i < j; ++i)
        {
            final Gene<?> g1 = lhs.get(i);
            final Gene<?> g2 = rhs.get(i);

            lhs.set(i,g2);
            rhs.set(i,g1);
        }

        return (rand.nextBoolean()) ? new Chromosome(lhs) : new Chromosome(rhs);
    }
}
