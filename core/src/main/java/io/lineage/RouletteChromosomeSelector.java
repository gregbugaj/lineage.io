package io.lineage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Roulette Wheel selection algorithem
 * @ref http://geneticalgorithms.ai-depot.com/Tutorial/Overview.html
 * @author gbugaj
 *
 */
public class RouletteChromosomeSelector implements ChromosomeSelector
{
    private static final Random SR = new SecureRandom();

    private final FitnessComparator FITNESS_COMPARATOR = new FitnessComparator();

    @Override
    public Pair<Chromosome, Chromosome> select(final List<Chromosome> population)
    {

        if (false)
        {
            Collections.sort(population, FITNESS_COMPARATOR);
            new Pair<Chromosome, Chromosome>(population.get(0), population.get(1));
        }

        double fitnessSum = 0d;
        for (final Chromosome c : population)
            fitnessSum += c.fitness;
        
        if (true)
        {
            final Chromosome c1 = choose(population, fitnessSum);
            final Chromosome c2 = choose(population, fitnessSum);

            return new Pair<Chromosome, Chromosome>(c1, c2);
        }

        /**
         */
        // roulette-wheel selection via stochastic acceptance
        final List<Chromosome> pair = new ArrayList<Chromosome>();
        final int size = population.size();
        int spins = 0;
        // esimated average spin t = fmax / avg(f) 
        for (;;)
        {
            ++spins;
            // Select randomly one of the individuals
            final Chromosome rc = population.get(SR.nextInt(size));
            final double r = SR.nextDouble();
            final double p = rc.fitness / fitnessSum;
            if (r < p)
            {
                //                System.out.println("SOLUTION : " + p + "  ::  " + rc);
                pair.add(rc);
                if (pair.size() == 2)
                    break;
            }
        }
        return new Pair<Chromosome, Chromosome>(pair.get(0), pair.get(1));

    }

    private Chromosome choose(final List<Chromosome> population, final double fitnessSum)
    {
        final float rnd = (float) (Math.random() * fitnessSum);
        double running = 0d;
        for (final Chromosome c : population)
        {
            if (rnd >= running && rnd <= running + c.fitness)
                return c;
            running += c.fitness;
        }

        return null;
    }
}
