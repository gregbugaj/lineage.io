package io.lineage;

public interface CrossoverOperator
{
    /**
     * 
     * @param parent1
     * @param parent2
     * @return pair of offsprings
     */
    Pair<Chromosome, Chromosome> crossover(final Chromosome parent1, final Chromosome parent2);

}
