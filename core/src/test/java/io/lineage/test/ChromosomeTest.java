package io.lineage.test;

import static org.junit.Assert.assertEquals;
import io.lineage.Chromosome;
import io.lineage.ChromosomeSelector;
import io.lineage.IntegerGene;
import io.lineage.Population;
import io.lineage.RouletteChromosomeSelector;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ChromosomeTest extends BaseTest
{
    private ChromosomeSelector selector;

    @Before
    public void init()
    {
        selector = new RouletteChromosomeSelector();
    }

    @Test
    public void spin()
    {
        final Chromosome a = new Chromosome();
        final Chromosome b = new Chromosome();

        assertEquals(a,b);
    }

    /**
     * http://stats.stackexchange.com/questions/117587/crossover-and-mutation-in-genetic-algorithm
     * 
     * http://www.omgwiki.org/hpec/files/hpec-challenge/ga.html
     * 
     * A crossover probability of 1.0 indicates that all the selected chromosomes are used in reproduction i.e. there are no survivors. However, empirical
     * studies have shown that better results are achieved by a crossover probability of between 0.65 and 0.85, which implies that the probability of a selected
     * chromosome surviving to the next generation unchanged (apart from any changes arising from mutation) ranges from 0.35 to 0.15.
     */
    public void checkProbability()
    {
        final Random rand = new SecureRandom();

        // 0.65 and 0.85

        final double p1 = 0.5;
        final double p2 = 0.8;

        final Population pop = new Population();
        final int total = 20;
        for (int i = 0; i < total; ++i)
        {
            pop.add(make(i));
        }

        final int popsize = pop.size();
        final int outcomes = popsize / 2;
        System.out.println(outcomes);

        System.out.println("outcomes  : " + outcomes);
        int count = 0;
        final double pc = (double)1 / popsize;
        System.out.println(pc);
        for (int i = 0; i < outcomes; ++i)
        {
            if (rand.nextDouble() < pc)
            // double r = rand.nextDouble();
            // if (r > p1 && r < p2)
            {
                ++count;
            }
        }

        System.out.println("count  : " + count);
    }

    @Test
    public void checkGeneMutationProbability()
    {
        final Chromosome ch = make(1);

        final int genes = 10;
        for (int i = 0; i < genes; ++i)
            ch.add(new IntegerGene(1,0,10));

        int total = 0;
        final int mutations = 10;
        for (int i = 0; i < mutations; ++i)
        {
            ch.mutate();
            total += ch.mutationsPerCycle;
        }
        
        final int k = genes * mutations;
        final double rat = (double)total / k;
        System.out.println("Total Mutations  : " + total  + " ratio : "  + rat);
    }

}
