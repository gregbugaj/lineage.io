package io.lineage;

/**
 * {@link Chromosome} decoder that takes converts them into crisp values.
 * 
 * @param <T> Type of value this chromosome returns
 */
public interface Decoder<T>
{
    T decode(final Chromosome chromosome);
}