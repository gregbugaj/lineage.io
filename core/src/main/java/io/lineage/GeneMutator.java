package io.lineage;

@FunctionalInterface
public interface GeneMutator<T>
{
    /**
     * Mutate given {@link Gene} at this time the {@link Chromosome} determined that {@link Gene} needs to be mutates
     * 
     * @param gene
     */
    void mutate(final T gene);
}
