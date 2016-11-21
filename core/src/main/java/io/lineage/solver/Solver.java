package io.lineage.solver;

import io.lineage.Population;

/**
 * Interface that each Solver need to implement It defines basic needed operations.
 * 
 * @see TimedSolver
 * @see IterationSolver
 */
public interface Solver
{
    /**
     * Find best solution to over given problem
     * 
     * @param population
     *            Initial population to find solution for
     *
     * @return {@link Population} of possible solutions
     */
    Population findSolution(final Population population);

    /**
     * Evolve given {@link Population} and return newly generated one
     * 
     * @param population
     * @return
     */
    Population evolve(final Population population);
}
