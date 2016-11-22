package io.lineage;

import java.util.BitSet;

/**
 * Base gene encoder
 */
public abstract class BaseDecoder<T> implements Decoder<T>
{
    private static short fromBitSetToShort(final BitSet bs)
    {
        return (short)fromBitSetToInt(bs,15);
    }

    private static byte fromBitSetToByte(final BitSet bs)
    {
        return (byte)fromBitSetToInt(bs,7);
    }

    private static int fromBitSetToInt(final BitSet bs, final int size)
    {
        int val = 0;
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1))
            val = (val | (1 << (size - i)));

        return val;
    }
}
