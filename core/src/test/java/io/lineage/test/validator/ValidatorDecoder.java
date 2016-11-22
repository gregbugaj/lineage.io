package io.lineage.test.validator;

import io.lineage.Chromosome;
import io.lineage.Decoder;
import io.lineage.Gene;

import java.util.List;

public class ValidatorDecoder implements Decoder<FunctionParams>
{

    @SuppressWarnings("unchecked")
    @Override
    public FunctionParams decode(final Chromosome chromosome)
    {
        final List<Gene<?>> genes = chromosome.getGenes();
        final FunctionParams data = new FunctionParams();

        data.a = (int)genes.get(0).getAllele();
        data.b = (int)genes.get(1).getAllele();
        data.c = (int)genes.get(2).getAllele();
        data.d = (int)genes.get(3).getAllele();
        data.e = (int)genes.get(4).getAllele();
        data.f = (int)genes.get(5).getAllele();
        data.g = (int)genes.get(6).getAllele();
        data.h = (int)genes.get(7).getAllele();

        return data;
    }
}
