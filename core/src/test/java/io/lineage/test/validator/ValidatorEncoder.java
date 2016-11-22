package io.lineage.test.validator;

import io.lineage.Chromosome;
import io.lineage.Encoder;
import io.lineage.IntegerGene;

/*chromosomes of the form x = abcdefgh
 with a fixed length of eight genes. Each gene can be any digit between 0
 and 9. Let the fitness of individual x be calculated as:
 f(x) = (a + b) − (c + d) + (e + f) − (g + h)
 */
public class ValidatorEncoder implements Encoder<FunctionParams>
{

    @Override
    public Chromosome encode(final FunctionParams data)
    {
        final Chromosome cm = new Chromosome();

        cm.add(new IntegerGene(data.a,0,9));
        cm.add(new IntegerGene(data.b,0,9));
        cm.add(new IntegerGene(data.c,0,9));
        cm.add(new IntegerGene(data.d,0,9));
        cm.add(new IntegerGene(data.e,0,9));
        cm.add(new IntegerGene(data.f,0,9));
        cm.add(new IntegerGene(data.g,0,9));
        cm.add(new IntegerGene(data.h,0,9));

        return cm;
    }

}
