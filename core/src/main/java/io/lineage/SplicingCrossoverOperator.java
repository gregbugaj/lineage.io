package io.lineage;

import java.security.SecureRandom;
import java.util.List;

/**
 * Default implementation of CrossoverOperator 
 * This implementation uses splicing method to create new offsprings
 * @author gbugaj
 *
 */
public class SplicingCrossoverOperator implements CrossoverOperator
{

    @Override
    public Pair<Chromosome, Chromosome> crossover(final Chromosome parent1, final Chromosome parent2)
    {
        final SecureRandom rand = new SecureRandom();
        // This genes are copy of the underlying parent
        final List<Gene<?>> lhs = parent1.getGenes();
        final List<Gene<?>> rhs = parent2.getGenes();
        final int crossoverPoint = rand.nextInt(lhs.size() - 1);
        for (int i = crossoverPoint, gs = lhs.size(); i < gs; ++i)
        {
            final Gene<?> g1 = lhs.get(i);
            final Gene<?> g2 = rhs.get(i);

            lhs.set(i, g2);
            rhs.set(i, g1);
        }
        
        // pair of newly created offspring
        return new Pair<Chromosome, Chromosome>(new Chromosome(lhs), new Chromosome(rhs));
    }
}
