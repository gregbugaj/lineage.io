package io.lineage;

import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.LoggerFactory;

/**
 * Gene mutator based on research from here
 * 
 * @ref http://www.muehlenbein.org/breeder93.pdf
 * @ref http://www.geatbx.com/docu/algindex-04.html#P666_44497
 * @ref www.cs.upc.edu/~belanche/research/Buran.ps.gz However, larger changes (large mutation steps) can, when successful, produce good results much quicker.
 *      Thus, a good mutation operator should often produce small step-sizes with a high probability and large step-sizes with a low probability.
 * 
 */
public class IntegerGeneMutator implements GeneMutator<IntegerGene>
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(IntegerGeneMutator.class);

    @Override
    public void mutate(final IntegerGene gene)
    {
        // The new value z is computed according to
        Random rand = new SecureRandom();
        int searchinterval = gene.max - gene.min;
        
        double r1 = .01 * searchinterval;
        int s1 = rand.nextBoolean()?-1:1;
        
        // Mutation precision [4,20]
        int k = 2;
        // in range [0,1]
        double u = rand.nextDouble();
        double a1 = Math.pow(2,-u * k);
        double z = s1 * r1 * a1;

        Integer allele = gene.getAllele();
        int newallele = (int)(allele + z);

        System.out.println("Search r1 : " + r1);
        System.out.println("Search s1 : " + s1);
        System.out.println("Search a1 : " + a1);
        System.out.println("Search z : " + z);
        System.out.println("Search allele : " + allele);
        System.out.println("Search newallele : " + allele);
        
        gene.setAllele(newallele);
    }
    
    public void breedersAlgorithem(final IntegerGene gene)
    {
        // The new value z is computed according to
        // z = val + s1 * r1 * a1
        
        Random rand = new SecureRandom();
        int searchinterval = gene.max - gene.min;
        double r1 = .01 * searchinterval;
        int s1 = rand.nextBoolean()?-1:1;
        // Mutation precision [4,20]
        int k = 2;
        // in range [0,1]
        double u = rand.nextDouble();
        double a1 = Math.pow(2,-u * k);
        double z = s1 * r1 * a1;
        
        Integer allele = gene.getAllele();
        int newallele = (int)(allele + z);
        
        System.out.println("Search r1 : " + r1);
        System.out.println("Search s1 : " + s1);
        System.out.println("Search a1 : " + a1);
        System.out.println("Search z : " + z);
        System.out.println("Search allele : " + allele);
        System.out.println("Search newallele : " + allele);
        
        gene.setAllele(newallele);
    }
}
