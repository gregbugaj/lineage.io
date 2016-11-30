package io.lineage.core;

import io.lineage.BaseEncoder;
import io.lineage.Chromosome;
import io.lineage.IntegerGene;

/**
 * Encoder used to encode given String value into a {@link Chromosome} Each string character is represented as one gene
 * 
 * @see {@link StringDecoder}
 */
public class StringEncoder extends BaseEncoder<String>
{

    @Override
    public Chromosome encode(final String data)
    {
        final Chromosome chromosome = new Chromosome();
        for (final char ch : data.toCharArray())
        {
            chromosome.add(new IntegerGene(ch, 65, 127));
        }
        return chromosome;
    }
}
