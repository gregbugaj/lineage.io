package io.lineage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chromosome represents a single solution to our problem. 
 * The term genotype refers to the particular set of genes contained in a genome
 * 
 * phenotype� gives physical and mental characteristics, such as eye color, height, brain size, and intelligence.
 */
public class Chromosome
{
    /**
     * Evaluated fitness of this solution
     */
    public double fitness;

    public List<Gene<?>> genes = new ArrayList<Gene<?>>();

    private final Random rand = new SecureRandom();

    public int mutationsPerCycle;
    
    public Chromosome()
    {

    }

    public Chromosome(final List<Gene<?>> genes)
    {
        this.genes = genes;
    }

    public int getGeneLenght()
    {
        return genes.size();
    }

    /**
     * Mutate current {@link Chromosome}. This is the chance that a value within a chromosome will be flipped (0 becomes 1, 1 becomes 0). This is usually a very
     * low value for binary encoded genes 100 if you have 1% mutation probability it means that 1 out of your 100 bits (on average) picked at random will be
     * flipped.
     * 
     * @ref http://www.geatbx.com/docu/algindex-04.html#P666_44497 
     *  http://www.optiwater.com/optiga/ga.html Each bit in each chromosome is checked for possible
     *      mutation by generating a random number between zero and one and if this number is less than or equal to the given mutation probability e.g. 0.001
     *      then the value is changed.
     */
    public void mutate()
    {
        mutationsPerCycle = 0;
        // mutation rate of 1/n
        final int n = genes.size();
        final double p = (double) 1 / n;
        
        for (final Gene<?> gene : genes)
        {
            if (rand.nextDouble() < p)
            {
                ++mutationsPerCycle;
                gene.mutate();
            }
        }
    }

    @Override
    public String toString()
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final Decoder<Object> decoder = context.getDecoder();

        final StringBuilder sb = new StringBuilder();
        sb.append("Fitness").append("\t").append(fitness).append("\t").append(genes).append(" -> ").append(decoder.decode(this))

        ;

        return sb.toString();
    }

    /**
     * Return copy of our genes
     * 
     * @return copy of our genes
     */
    public List<Gene<?>> getGenes()
    {
        try
        {
            final List<Gene<?>> copy = new ArrayList<>();
            for (final Gene<?> g : genes)
            {
                copy.add(g.clone());
            }

            return copy;
        }
        catch (final Throwable t)
        {
            throw new RuntimeException("we are not able to make a copy of our genes", t);
        }
    }

    /**
     * @param genes
     *            the genes to set
     */
    public void setGenes(final List<Gene<?>> genes)
    {
        this.genes = genes;
    }

    public void add(final Gene<?> gene)
    {
        if (gene == null)
            throw new NullPointerException("Gene can't be null");

        this.genes.add(gene);
    }

    /**
     * Equivalence is made by checking each gene against each other
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof Chromosome))
            return false;

        final Chromosome that = (Chromosome)obj;

        for (final Gene<?> g1 : genes)
        {
            boolean found = false;
            for (final Gene<?> g2 : that.genes)
            {
                if (g1.equals(g2))
                {
                    found = true;
                    break;
                }
            }
            if (found == false)
                return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 1;
        for (final Gene<?> g1 : genes)
        {
            hash = 31 * hash + g1.hashCode();
        }
        return hash;
    }
}
