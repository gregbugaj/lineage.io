package io.lineage.test;

import org.junit.Test;

/**
 * Test bacis encodingig decoding functionality
 * @author gbugaj
 *
 */
public class BaseEncoderTest
{

    @Test
    public void testByteEncoding()
    {
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; ++i)
        {
         /*   final byte p1 = i;
            final Gene gene = BaseEncoder.makeByteGene(p1);
            final byte out = BaseDecoder.fromBitSetToByte(gene.getBits());
            assertEquals(p1, out);*/
        }
    }

    @Test
    public void testShortEncoding()
    {
        for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; ++i)
        {
//            final short p1 = i;
//            final Gene gene = BaseEncoder.makeShortGene(p1);
//            final short out = BaseDecoder.fromBitSetToShort(gene.getBits());
//            assertEquals(p1, out);
        }
    }
}
