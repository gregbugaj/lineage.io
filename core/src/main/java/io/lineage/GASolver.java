package io.lineage;

import java.util.concurrent.TimeUnit;

/**
 * GA Solver
 * @author gbugaj
 *
 */
public class GASolver
{

    public GASolver()
    {

    }

    public void findSolution(final Genotype genome)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final double threashold = context.getAcceptableFitnessScore();

        if (context.isTimedIteration())
        {
            System.out.println("Running with TIMED Iterations : " + context.getTimedIterationTime());

            for (final long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(context.getTimedIterationTime()); stop > System
                .nanoTime();)
            {
                genome.evolve();
                final Chromosome fittest = getFittest(genome);
                if (fittest.fitness >= threashold)
                    break;
            }
        }
        else
        {
            System.out.println("Running with FIXED Iterations : " + context.getIterationCount());

            for (int i = 0; i < context.getIterationCount(); ++i)
            {
                genome.evolve();
                final Chromosome fittest = getFittest(genome);
                if (fittest.fitness >= threashold)
                    break;
            }
        }
    }

    private Chromosome getFittest(final Genotype population)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();

        final Chromosome fittest = population.getFittest();
        System.out.println("Generation # " + population.getGeneration() + " fittest : " + fittest.fitness + " : "
            + fittest + " > " + context.getDecoder().decode(fittest));
        return fittest;
    }
}
