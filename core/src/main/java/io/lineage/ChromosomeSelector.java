package io.lineage;

/**
 * Chromosome selector that selects best two fitted {@link Chromosome} for mating from given population.
 */
public interface ChromosomeSelector
{
    /**
     * Return next best two fitted {@link Chromosomes} from given population
     * 
     * @param population
     *            to select parent from
     * @return
     */
    Pair<Chromosome, Chromosome> selectPair(final Population population);

    Chromosome select(final Population population);
}
