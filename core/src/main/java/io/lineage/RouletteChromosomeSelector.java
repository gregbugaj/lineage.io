package io.lineage;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * roulette-wheel selection via stochastic acceptance
 * 
 * Roulette Wheel selection algorithem https://en.wikipedia.org/wiki/Fitness_proportionate_selection
 * 
 * @ref http://geneticalgorithms.ai-depot.com/Tutorial/Overview.html
 * 
 *      Roulette-wheel selection via stochastic acceptance http://arxiv.org/pdf/1109.3627.pdf
 * 
 *      https://www.researchgate.net/publication/51962025_Roulette-wheel_selection_via_stochastic_acceptance
 *
 */
public class RouletteChromosomeSelector implements ChromosomeSelector
{

    public Pair<Chromosome, Chromosome> select000(final Population population)
    {
        double sum = 0d;
        for (final Chromosome c : population)
        {
            sum += c.fitness;
        }
        return new Pair<Chromosome, Chromosome>(choose(population,sum),choose(population,sum));
    }

    @Override
    public Pair<Chromosome, Chromosome> selectPair(final Population population)
    {
        final Population clone = population.clone();
        final Chromosome p1 = select(clone);
        clone.removeAll(Arrays.asList(p1));
        Chromosome p2 = null;
        p2 = select(clone);
        return new Pair<Chromosome, Chromosome>(p1,p2);
    }

    @Override
    public Chromosome select(final Population population)
    {
        final Random rand = new SecureRandom();
        double maxFitness = Integer.MIN_VALUE; //Double.MIN_VALUE;

        for (final Chromosome c : population)
        {
            /*
                 if (c.fitness < 0)
                throw new IllegalStateException("Fittness score should be in [0, 1] range but got " + c.fitness );
             */
            System.out.println(c.fitness + " :: " + maxFitness);
            if (c.fitness > maxFitness)
                maxFitness = c.fitness;
        }

        final int size = population.size();
        int spins = 0;

        if (maxFitness == 0)
        {
            return population.get(rand.nextInt(size));
        }

        // esimated average spin t = fmax / avg(f)
        Chromosome candidate = null;
        for (;;)
        {
            ++spins;
            // Select randomly one of the individuals
            candidate = population.get(rand.nextInt(size));
            final double r = rand.nextDouble();
            final double p = candidate.fitness / maxFitness;

            if (r < p)
            {
                return candidate;
            }
        }
    }

    private Chromosome choose(final Population population, final double fitnessSum)
    {
        while (true)
        {
            final float rnd = (float)(Math.random() * fitnessSum);
            double running = 0d;
            for (final Chromosome c : population)
            {
                if (rnd >= running && rnd <= (running + c.fitness))
                {
                    return c;
                }
                running += c.fitness;
            }
        }
    }

}
