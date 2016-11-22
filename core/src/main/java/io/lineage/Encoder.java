package io.lineage;

public interface Encoder<T>
{
    /**
     * Encode given data into specific {@link Chromosome}
     * 
     * @param data to encode
     * @return encoded {@link Chromosome}
     */
    Chromosome encode(final T data);
}