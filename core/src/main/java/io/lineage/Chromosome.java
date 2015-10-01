package io.lineage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chromosome represents a single solution to our problem 
 * Each chromosome is made up of several genes
 */
public class Chromosome
{
    public double fitness;

    public List<Gene<?>> genes = new ArrayList<Gene<?>>();

    public Chromosome()
    {

    }

    public Chromosome(final List<Gene<?>> genes)
    {
        this.genes = genes;
    }

    public static interface Encoder<T>
    {
        Chromosome encode(final T data);
    }

    public static interface Decoder<T>
    {
        T decode(final Chromosome chromosome);
    }

    public int getGeneLenght()
    {
        return genes.size();
    }


    /**
     * Mutate current chromosome
     * This is the chance that a bit within a chromosome will be flipped (0 becomes 1, 1 becomes 0).
     *  This is usually a very low value for binary encoded genes
     */
    public void mutate()
    {
        final GAExecutionContext context = GAExecutionContext.currentExecutionContext();
        final double mutationRate = context.mutationRate;
        final Random r = new SecureRandom();
        //        System.out.println("gene before : " + genes);
        for (int i = genes.size() - 1; i >= 0; --i)
        {
            float mr = r.nextFloat();
            if (mr < mutationRate)
            {
                System.out.println("Mutating : " + mr);
                genes.get(i).mutate();
            }
        }
        //        System.out.println("gene after  : " + genes);
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append(genes.toString());

        return builder.toString();
    }

    /**
     * 
     * @return copy of our genes
     */
    public List<Gene<?>> getGenes()
    {
        try
        {
            final List<Gene<?>> copy = new ArrayList<Gene<?>>();
            for (final Gene<?> g : genes)
                copy.add(g.clone());

            return copy;
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
        }

        throw new RuntimeException("we are not able to make a copy of our genes");
    }

    /**
     * @param genes the genes to set
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
}
