package io.lineage;

import java.util.List;

/**
 * Chromosome selector
 * @author gbugaj
 *
 */
public interface ChromosomeSelector
{
    Pair<Chromosome, Chromosome> select(final List<Chromosome> population);
}
