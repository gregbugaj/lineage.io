package io.lineage;

@FunctionalInterface
public interface CrossoverOperator
{
    /**
     * Crate a crossover between to parents
     * 
     * @param parent1
     * @param parent2
     * @return  new offspring
     */
    Chromosome crossover(final Chromosome parent1, final Chromosome parent2);
}
