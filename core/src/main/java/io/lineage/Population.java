package io.lineage;

import static io.lineage.Assert.notNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Population implements Iterable<Chromosome>, Cloneable
{

    /**
     * Generation that this population was generated at.
     */
    int generation;

    /**
     * Fitesst chromosome
     */
    private Chromosome fittest;

    private List<Chromosome> population;

    public Population()
    {
        population = new ArrayList<>();
    }

    public Population(final List<Chromosome> population)
    {
        notNull(population);
        this.population = population;
    }

    public void add(final Chromosome chromosome)
    {
        notNull(chromosome);
        population.add(chromosome);
    }

    public Chromosome get(final int index)
    {
        return population.get(index);
    }

    @Override
    public Iterator<Chromosome> iterator()
    {
        return population.iterator();
    }

    public Chromosome getFittest()
    {
        return fittest;
    }

    public void setFittest(final Chromosome fittest)
    {
        notNull(fittest);
        // System.out.println("Setting fittest : " + fittest);
        // System.out.println("    -- CH " + fittest.hashCode());
        this.fittest = fittest;
    }

    public int size()
    {
        return population.size();
    }

    public void set(final List<Chromosome> population)
    {
        Objects.nonNull(population);
        this.population = population;
    }

    public void addAll(final List<Chromosome> chromosomes)
    {
        Objects.nonNull(chromosomes);
        this.population.addAll(chromosomes);
    }

    public boolean contains(final Chromosome next)
    {
        return this.population.contains(next);
    }

    @Override
    protected Population clone()
    {
        return  new Population(new ArrayList<>(population));
    }

    public void dump(final String msg)
    {
        System.out.println(msg);
        for (final Chromosome c : population)
        {
            System.out.println("  -- " + c);
        }

    }

    public void sort()
    {
        final Comparator<Chromosome> fitnessComparator = GAExecutionContext.currentExecutionContext().getFitnessComparator();
        Collections.sort(population,fitnessComparator);
    }

    public List<Chromosome> getRange(final int fromIndex, final int toIndex)
    {
        return population.subList(fromIndex,toIndex);
    }

    /**
     * Remove elements from given population
     * @param elements
     */
    public void removeAll(final Collection<Chromosome> elements)
    {
        population.removeAll(elements);
    }
}
