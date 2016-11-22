package io.lineage;

import io.lineage.AcceptanceEvaluator.Evaluation;
import io.lineage.solver.Solver;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.omgwiki.org/hpec/files/hpec-challenge/ga.html http://www.optiwater.com/optiga/ga.html
 * https://www.researchgate.net/post/How_to_calculate_the_Crossover_Mutation_rate_and_population_size_for_Genetic_algorithm
 *
 */
public abstract class BaseSolver implements Solver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSolver.class);

    /**
     * Execute one single cycle
     * 
     * @param population
     * @param result
     * @return true if continue with next cycle false otherwise
     */
    protected boolean cycle(final Population population, final Population result)
    {
        LOGGER.info("CYCLE START ------------------------------------");
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();

        final long s = System.nanoTime();
        try
        {
            final Population evolved = evolve(population);
            final Chromosome fittest = evolved.getFittest();

            if (fittest == null)
                throw new IllegalStateException("We have to have a fittest ");

            final AcceptanceEvaluator evaluator = context.getAcceptanceEvaluator();
            final Evaluation evaluation = evaluator.evaluate(fittest);

            if (evaluation.accepted)
            {
                result.add(evaluation.selection);
            }

            return evaluation.stopNextCycle;
        }
        finally
        {
            logCycleTime(System.nanoTime() - s);
            LOGGER.info("CYCLE END ------------------------------------");
        }

    }

    public static void determineFitness(final Population population)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final FitnessSelector fitnessSelector = context.getFitnessSelector();
        double max = -1;

        for (final Chromosome current : population)
        {
            fitnessSelector.evaluate(current);
            System.out.println("current  " + current.fitness + " :: " + max);
            if (current.fitness > max)
            {
                max = current.fitness;
                population.setFittest(current);
            }
        }

        if (population.getFittest() == null)
            throw new IllegalStateException("Somehow we did not get the best fitnes score");

        population.sort();
    }

    public List<Chromosome> getNFittest(final Population population, final int best)
    {
        final List<Chromosome> selection = population.getRange(0,best);
        for (final Chromosome c : selection)
        {
            System.out.println("  BEST   Survivor --- " + c);
        }
        return new ArrayList<>(selection);
    }

    public Population evolve(final Population in)
    {
        System.out.println("\n\n");
        final Random random = new SecureRandom();
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();

        System.out.println("#### Evolving Population  :: " + context.currentGeneration);

        final ChromosomeSelector chromosomeSelector = context.getChromosomeSelector();
        final CrossoverOperator crossoverOperator = context.getCrossoverOperator();

        final Population nextpop = new Population();
        nextpop.generation = ++context.currentGeneration;

        final Population population = in.clone();
        final int size = population.size();
        final int esize = (int)Math.ceil((.2 * size));

        // elitism : For each iteration, the best 20% of the chromosomes from the old
        // population are copied to the new population
        final List<Chromosome> survivors = getNFittest(population,esize);
        nextpop.addAll(survivors);
        nextpop.dump(" *** Survivors ");

        population.removeAll(survivors);

        int spin = 0;
        int crossovers = 0;

        final Random rand = new SecureRandom();

        System.out.println("nextpop : INIT SIZE : " + nextpop.size());
        
        while (nextpop.size() < size)
        {
            Pair<Chromosome, Chromosome> best = chromosomeSelector.selectPair(population);

            System.out.println("A > " + best.getA());
            System.out.println("B > " +best.getB());

            
            final double r = rand.nextDouble();
            // if (r > p1 && r < p2)
            if (r < context.crossoverRate)
            {
                // System.out.println("+++ Crossover");
              best = crossoverOperator.crossover(best.getA(),best.getB());
                ++crossovers;
            }

            final Chromosome a = best.getA();
            
            /*
            if (rand.nextDouble() <= context.mutationRate)
            {
                a.mutate();
            }
*/
            // if (!nextpop.contains(a))
            {
                nextpop.add(a);
            }

            ++spin;
        }

        System.out.println(" ----- EvolveSpins, crossovers: " + spin + " , " + crossovers);

        determineFitness(nextpop);
        System.out.println("Best Solution : " + nextpop.getFittest());
        return nextpop;
    }

    protected void logTotalTime(final int itertation, final long durationInNanos)
    {
        LOGGER.info("Stopped at iteration # {} after {} (ms) ",itertation,TimeUnit.NANOSECONDS.toMillis(durationInNanos));
    }

    private void logCycleTime(final long durationInNanos)
    {
        LOGGER.info("Single Run tooks {} ms",TimeUnit.NANOSECONDS.toMillis(durationInNanos));
    }

}
