package io.lineage.test;

import io.lineage.Chromosome;

public class BaseTest
{
    public Chromosome make(double fitness)
    {
        final Chromosome c = new Chromosome();
        c.fitness = fitness;
        return c;
    }
}
