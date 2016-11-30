package io.lineage;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Gene of type Integer
 */
public class IntegerGene extends Gene<Integer>
{
    final int min;

    final int max;

    final Random rand = new SecureRandom();

    public IntegerGene(final int value, final int min, final int max)
    {
        this.min = min;
        this.max = max;
        this.allele = value;
    }

    @Override
    public void mutate()
    {
        System.out.println("MUTATING GENE");
        final int mutmin = min;
        final int mutmax = max;
        final int rval = mutmin + rand.nextInt(mutmax - mutmin + 1);

        // clamp
        int v = Math.min(max, rval);

        // System.out.println("  MUATING : " + rval + "   > " + (char)(this.allele.intValue()) + " :: " + (char)v);
        // this.value = Math.max(min,this.value - rval);
        this.allele = v;

    }

    @Override
    protected IntegerGene clone() throws CloneNotSupportedException
    {
        return new IntegerGene(allele, min, max);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof IntegerGene))
            return false;

        final IntegerGene that = (IntegerGene) obj;
        return this.allele.equals(that.allele);
    }

    @Override
    public int hashCode()
    {
        return this.allele.hashCode();
    }

}
