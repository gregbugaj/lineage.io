package io.lineage;

/**
 * Lowest building block
 */
public abstract class Gene<T>
{
    /**
     * Value represented by this gene
     */
    protected T allele;

    /**
     * Clone current gene
     */
    @Override
    protected abstract Gene<T> clone() throws CloneNotSupportedException;

    /**
     * Called when gene needs to mutate self
     */
    public abstract void mutate();

    /**
     * @param allele the value to set
     */
    public void setAllele(final T allele)
    {
        this.allele = allele;
    }

    public T getAllele()
    {
        return allele;
    }
    
    
    @Override
    public String toString()
    {
        return "G :" + allele;
    }
    
}
