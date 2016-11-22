package io.lineage;

/**
 * 
 */
public interface FitnessSelector
{
    /**
     * Calculate fitness of given population against given target
     * 
     * @param solutions
     * @param target
     */
//    void evaluate(final Population solutions, final Chromosome target);
    
    /**
     * Calculate fitness of given indiviudal
     * @param target
     */
    void evaluate(final Chromosome target);
}
