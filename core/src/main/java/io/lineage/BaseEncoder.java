package io.lineage;

import io.lineage.Chromosome.Encoder;

import java.util.BitSet;


/**
 * Base gene encoder
 */
public abstract class BaseEncoder<T> implements Encoder<T>
{

    public static final int GENE_SIZE_BOOL = 1;

    public static final int GENE_SIZE_BYTE = 8;

    public static final int GENE_SIZE_SHORT = 16;

    public static final int GENE_SIZE_INT = 32;

    protected static final boolean[] NEGATIVE = new boolean[] { false };

    protected static final boolean[] POSITIVE = new boolean[] { true };

    public static boolean[] asIntBits(int val, final int size)
    {
        final boolean[] out = new boolean[size];
        for (int i = size - 1; i >= 0; --i, val >>= 1)
            out[i] = ((val & 1) == 1);

        return out;
    }

    public static void appendBinary(final BitSet bs, final int fromIndex, final boolean[] bindata)
    {
        for (int i = 0, len = bindata.length; i < len; ++i)
            bs.set(fromIndex + i, bindata[i]);
    }

    /**
     * Byte data type is an 8-bit signed two's complement integer.
     * Minimum value is -128 (-2^7)
     * Maximum value is 127 (inclusive)(2^7 -1)
     * @param p1
     * @return
     */
    public static Gene makeByteGene(final byte p1)
    {
        return makeIntBySize(p1, GENE_SIZE_BYTE);
    }

    /**
     * Short data type is a 16-bit signed two's complement integer.
     * Minimum value is -32,768 (-2^15)
     * Maximum value is 32,767 (inclusive) (2^15 -1)
     * @param p1
     * @return
     */
    public static Gene makeShortGene(final short p1)
    {
        return makeIntBySize(p1, GENE_SIZE_SHORT);
    }

    /**
     * Int data type is a 32-bit signed two's complement integer.
     *
     * Minimum value is - 2,147,483,648.(-2^31)
     * Maximum value is 2,147,483,647(inclusive).(2^31 -1)
     * @param p1
     * @return
     */
    public static Gene makeIntGene(final int p1)
    {
        return makeIntBySize(p1, GENE_SIZE_INT);
    }

    private static Gene makeIntBySize(int p1, int geneSizeInt)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Encode boolean values as 1-bit 
     * @param state
     * @return
     
    public static Gene makeBooleanGene(final boolean state)
    {
        final BitSet bs = new BitSet(GENE_SIZE_BOOL);
        appendBinary(bs, 0, state ? POSITIVE : NEGATIVE);
        return new Gene(bs, GENE_SIZE_BOOL);
    }
    */

    /*
    public static Gene makeIntBySize(final int p1, final int size)
    {
        final BitSet bs = new BitSet(size);
        appendBinary(bs, 0, asIntBits(p1, size));
        return new Gene(bs, size);
    }
    */
}
