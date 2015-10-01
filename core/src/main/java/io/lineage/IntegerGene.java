package io.lineage;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Gene of type Integer
 */
public class IntegerGene extends Gene<Integer>
{

    private final int min;

    private final int max;

    public IntegerGene(final int value, final int min, final int max)
    {
        this.min = min;
        this.max = max;
        this.value = value;
    }

    @Override
    public void mutate()
    {
        final int mutmin = 3;
        final int mutmax = 9;

        final Random rand = new SecureRandom();
        final int v = mutmin + rand.nextInt(mutmax - mutmin + 1);
        if (rand.nextBoolean())
            this.value = Math.min(max,this.value + v);
        else
            this.value = Math.max(min,this.value - v);
    }

    @Override
    protected Gene<Integer> clone() throws CloneNotSupportedException
    {
        return new IntegerGene(value,min,max);
    }
}
