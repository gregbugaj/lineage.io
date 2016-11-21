package io.lineage.solver;

import io.lineage.BaseSolver;
import io.lineage.Population;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solver that will terminate upon certain number of iterations if no solution have been found during that period.
 */

public class IterationSolver extends BaseSolver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IterationSolver.class);

    private final int iterations;

    private IterationSolver(final Builder builder)
    {
        iterations = builder.iterations;
    }

    public Population findSolution(final Population population)
    {

        LOGGER.info("Running with FIXED Iterations : {} ",iterations);
        final Population result = new Population();

        int itr = 0;
        final long s = System.nanoTime();

        determineFitness(population);

        System.out.println("Best from Initial Pop " + population.getFittest());

        for (int i = 0; i < iterations; ++i, itr = i)
        {
            if (cycle(population,result))
                break;
        }

        logTotalTime(itr,System.nanoTime() - s);

        return result;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private int iterations;

        public Builder iterations(final int iterations)
        {
            this.iterations = iterations;
            return this;
        }

        public Solver build()
        {
            if (iterations <= 0)
                throw new IllegalAccessError("iterations can't be negative");

            return new IterationSolver(this);
        }
    }

    public static void main(final String[] args)
    {
        final IterationSolver.Builder builder = IterationSolver.builder();
        final Solver solver = builder.build();
    }
}
