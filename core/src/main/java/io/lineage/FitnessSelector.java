package io.lineage;

/**
 * 
 * @author gbugaj
 *
 */
public interface FitnessSelector
{
    /**
     * Calculate fintess of given chromosome
     * @param source chromosome
     * @param target  chromosome
     * @return
     */
    double fitness(final Chromosome target);
}
