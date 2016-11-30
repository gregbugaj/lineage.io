package io.lineage.core;

import java.util.List;

import io.lineage.BaseDecoder;
import io.lineage.Chromosome;
import io.lineage.Gene;

/**
 * Decoder used to decode given {@link Chromosome} into a String value.
 * 
 * @see {@link StringEncoder}
 */
@SuppressWarnings("unchecked")
public class StringDecoder extends BaseDecoder<String>
{
    @Override
    public String decode(final Chromosome chromosome)
    {
        final List<Gene<?>> genes = chromosome.getGenes();
        final StringBuffer buff = new StringBuffer();

        for (final Gene<?> g : genes)
        {
            final Gene<Integer> gi = (Gene<Integer>) g;
            final char c = (char) gi.getAllele().intValue();
            
            buff.append(c);
        }

        return buff.toString();
    }

}
