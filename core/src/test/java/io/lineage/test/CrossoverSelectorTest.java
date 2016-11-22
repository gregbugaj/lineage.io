package io.lineage.test;

import io.lineage.Chromosome;
import io.lineage.CrossoverOperator;
import io.lineage.Pair;
import io.lineage.SplicingCrossoverOperator;
import io.lineage.core.StringDecoder;
import io.lineage.core.StringEncoder;

import org.junit.Test;

public class CrossoverSelectorTest
{

    @Test
    public void crossovertest()
    {
        final StringEncoder encoder = new StringEncoder();
        final StringDecoder decoder = new StringDecoder();
        
        final CrossoverOperator operator = new SplicingCrossoverOperator();

        final Chromosome c1 = encoder.encode("AAAAAAAAA");
        final Chromosome c2 = encoder.encode("BBBBBBBBB");
        
        Pair<Chromosome, Chromosome> crossover = operator.crossover(c1,c2);
        
        System.out.println(crossover.getA());
        System.out.println(crossover.getB());
        
        String s1 = decoder.decode(crossover.getA());
        String s2 = decoder.decode(crossover.getB());
        
        System.out.println(s1);
        System.out.println(s2);
    }
}
