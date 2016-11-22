package io.lineage;

import java.util.Comparator;

public class FitnessComparator implements Comparator<Chromosome>
{
    @Override
    public int compare(final Chromosome o1, final Chromosome o2)
    {
        return Double.compare(o1.fitness,o2.fitness) * -1;
    }
}
