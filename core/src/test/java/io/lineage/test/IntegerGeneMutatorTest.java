package io.lineage.test;

import io.lineage.IntegerGene;
import io.lineage.IntegerGeneMutator;

import org.junit.Test;

public class IntegerGeneMutatorTest
{
    @Test
    public void mutatorTest()
    {
        final IntegerGeneMutator mutator = new IntegerGeneMutator();
        final IntegerGene gene = new IntegerGene(5,0,10);

        int steps = 100;
        for (int i = 0; i < steps; ++i)
        {
            mutator.mutate(gene);
        }
    }
}
