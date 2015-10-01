package io.lineage.test;

import static org.junit.Assert.assertNotNull;
import io.lineage.Chromosome;
import io.lineage.ChromosomeSelector;
import io.lineage.Pair;
import io.lineage.RouletteChromosomeSelector;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class RouletteChromosomeSelectorTest
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
        final Chromosome c = new Chromosome();
        final Chromosome d = new Chromosome();
        final Chromosome e = new Chromosome();

        a.fitness = 1.5;
        b.fitness = 2.3;
        c.fitness = 6;
        d.fitness = 4;
        e.fitness = 6;

        final Pair<Chromosome, Chromosome> selections = selector.select(Arrays.asList(a, b, c, d, e));
        final Chromosome s1 = selections.getA();
        final Chromosome s2 = selections.getB();

        assertNotNull(s1);
        assertNotNull(s2);
        
        System.out.println("S1 : " + s1.fitness);
        System.out.println("S2 : " + s2.fitness);
    }
}
