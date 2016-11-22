package io.lineage.test;

import io.lineage.Chromosome;
import io.lineage.ChromosomeSelector;
import io.lineage.Population;
import io.lineage.RouletteChromosomeSelector;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

public class RouletteChromosomeSelectorTest extends BaseTest
{
    private ChromosomeSelector selector;

    @Before
    public void init()
    {
        selector = new RouletteChromosomeSelector();
    }

    @Test
    public void testNoScoreSelection()
    {
        final Population pop = new Population();

        pop.add(make(0));
        pop.add(make(1));
        pop.add(make(0));
        pop.add(make(0));

        final Chromosome s1 = selector.select(pop);

        System.out.println(pop);

    }

   

    @Test
    public void spinForNiceChoice()
    {
        final Random rand = new SecureRandom();
        final Population pop = new Population();

        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;

        for (int i = 0; i < 5; ++i)
        {
            final Chromosome a = make(i);
            pop.add(a);

            if (a.fitness < min)
                min = a.fitness;

            if (a.fitness > max)
                max = a.fitness;

            System.out.println("a.fitness ; " + a.fitness);
        }

        final Chromosome a = new Chromosome();
        a.fitness = 10;
        pop.add(a);

        System.out.println("Min,Max ::  " + min + "," + max);
        final ListMultimap<Integer, Chromosome> totals = MultimapBuilder.hashKeys().arrayListValues().build();

        int selections = 1000;
        for (int i = 0; i < selections; ++i)
        {
            final Chromosome s1 = selector.select(pop);
            totals.put((int)s1.fitness,s1);
        }

        System.out.println("Distributions");
        for (Integer key : totals.keySet())
        {
            List<Chromosome> items = totals.get(key);
            int rat = (int)(((double)items.size() / selections) * 100);
            System.out.println(key + " :: " + items.size() + "  > " + rat + " %");
        }
    }

}
