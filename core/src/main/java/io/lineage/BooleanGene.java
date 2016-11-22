package io.lineage;

/**
 * Boolean gene
 *
 */
public class BooleanGene extends Gene<Boolean>
{

    public BooleanGene(final boolean value)
    {
        this.allele = value;
    }

    @Override
    public void mutate()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected Gene<Boolean> clone() throws CloneNotSupportedException
    {
        return new BooleanGene(allele);
    }
}
