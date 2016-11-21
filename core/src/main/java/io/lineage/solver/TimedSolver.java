package io.lineage.solver;

import io.lineage.BaseSolver;
import io.lineage.Population;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solver that will terminate upon certain amount of time being passed and return the best solution find in that timeframe
 */
public class TimedSolver extends BaseSolver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedSolver.class);

    private TimeUnit unit;

    private long time;

    private TimedSolver(final Builder builder)
    {
        this.unit = builder.unit;
        this.time = builder.time;
    }

    @Override
    public Population findSolution(final Population population)
    {

        LOGGER.info("Running with TIMED Solver : {}, {} ",unit,time);
        final Population result = new Population();

        int iteration = 0;
        long s = System.nanoTime();

        determineFitness(population);

        for (final long stop = System.nanoTime() + unit.toNanos(time); stop > System.nanoTime(); ++iteration)
        {
            if (cycle(population,result))
                break;
        }

        logTotalTime(iteration,System.nanoTime() - s);

        return result;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private TimeUnit unit;

        private long time;

        public Builder expiration(final long time, final TimeUnit unit)
        {
            this.time = time;
            this.unit = unit;
            return this;
        }

        public Solver create()
        {
            return new TimedSolver(this);
        }
    }
}
