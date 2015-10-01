package io.lineage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Genotype reprseents a population of our chromosomes(solutions)
 * @author gbugaj
 *
 */
public class Genotype
{
    private List<Chromosome> chromosomes;

    private int generation;

    private final Comparator<Chromosome> fitnessComparator = new FitnessComparator();

    /**
    * @return the chromosomes
    */
    public List<Chromosome> getChromosomes()
    {
        return chromosomes;
    }

    /**
     * @param chromosomes the chromosomes to set
     */
    public void setChromosomes(final List<Chromosome> chromosomes)
    {
        this.chromosomes = chromosomes;
    }

    public void evolve()
    {
        ++generation;
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final ChromosomeSelector chromosomeSelector = context.getChromosomeSelector();
        final CrossoverOperator crossoverOperator = context.getCrossoverOperator();

        final List<Chromosome> currentpop = new ArrayList<Chromosome>(chromosomes);

        determineFitness(currentpop);
        // Select two members from the current population. 
        final List<Chromosome> nextpop = new ArrayList<Chromosome>();

        final SecureRandom r = new SecureRandom();
        final int size = chromosomes.size();

        // Elitis
        List<Chromosome> survivors = getFittest(4);
        nextpop.addAll(survivors);
        
        while (nextpop.size() < size)
        {
            final Pair<Chromosome, Chromosome> parents = chromosomeSelector.select(currentpop);
            final Chromosome nc;
            if (r.nextDouble() > context.crossoverRate)
            {
                final Pair<Chromosome, Chromosome> offsprings = crossoverOperator.crossover(parents.getA(),
                                                                                            parents.getB());
                // We only pick one offspring
                nc = offsprings.getA();
                nc.mutate();
            }
            else
            {
                nc = parents.getA();
            }
            nextpop.add(nc);
        }

        /*
         * for (int i = 0, k = chromosomes.size(); i < k; ++i) { final
         * Chromosome c = nextpop.get(i); c.mutate(); }
         */

        determineFitness(nextpop);
        chromosomes = nextpop;
    }

    private List<Chromosome> getFittest(int best)
    {
        Collections.sort(chromosomes, fitnessComparator);
        return chromosomes.subList(0, best); 
    }

    public void evolveX()
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final ChromosomeSelector chromosomeSelector = context.getChromosomeSelector();
        final CrossoverOperator crossoverOperator = context.getCrossoverOperator();

        final List<Chromosome> currentpop = new ArrayList<Chromosome>(chromosomes);

        determineFitness(currentpop);

        System.out.println("--- Population ");
        // Select two members from the current population. 
        final List<Chromosome> nextpop = new ArrayList<Chromosome>();

        final SecureRandom r = new SecureRandom();
        for (int i = 0, k = chromosomes.size(); i < k; ++i)
        {
            final Pair<Chromosome, Chromosome> parents = chromosomeSelector.select(currentpop);
            final Chromosome nc;
            if (r.nextDouble() > context.crossoverRate)
            {
                final Pair<Chromosome, Chromosome> offsprings = crossoverOperator.crossover(parents.getA(),
                                                                                            parents.getB());
                // We only pick one offspring
                nc = offsprings.getA();
            }
            else
            {
                nc = parents.getA();
            }

            System.out.println("NC : " + nc.fitness);
            nextpop.add(nc);
        }

        for (int i = 0, k = chromosomes.size(); i < k; ++i)
        {
            final Chromosome c = nextpop.get(i);
            c.mutate();
        }

        //        

        determineFitness(nextpop);
        chromosomes = nextpop;
    }

    public Chromosome getFittest()
    {
        Collections.sort(chromosomes, fitnessComparator);
        final Chromosome mc = chromosomes.get(0);
        return mc;
    }

    private void determineFitness(final List<Chromosome> population)
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final FitnessSelector fitnessSelector = context.getFitnessSelector();

        //Test each chromosome to see how good it is at solving the problem 
        for (int i = population.size() - 1; i >= 0; --i)
        {
            final Chromosome c = population.get(i);
            final double fitness = fitnessSelector.fitness(c);
            c.fitness = fitness;
        }
    }

    /**
     * @return the generation
     */
    public int getGeneration()
    {
        return generation;
    }
}
