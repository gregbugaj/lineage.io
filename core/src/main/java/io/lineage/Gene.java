package io.lineage;

/**
 * Lowest building block
 * 
 * @author gbugaj
 */
public abstract class Gene<T>
{
    protected T value;

    public T getValue()
    {
        return value;
    }

    /*
     * public Gene(final BitSet bits, final int encodingLength) { this.bits =
     * bits; this.encodingLength = encodingLength; }
     */

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
     * @param value the value to set
     */
    public void setValue(final T value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "G :" + value;
    }

}
