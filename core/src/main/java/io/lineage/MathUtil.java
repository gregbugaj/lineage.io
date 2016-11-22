package io.lineage;

/**
 * math helper functions
 *
 */
public class MathUtil
{
    public static final double DBL_EPSILON = findMachineDoubleEpsilon();
    
    public static final double FLT_EPSILON = findMachineFloatEpsilon();

    private static double findMachineDoubleEpsilon()
    {
        double tmp = .5;
        while ((1 + tmp) > 1)
        {
            tmp = tmp / 2;
        }
        return tmp;
    }

    private static float findMachineFloatEpsilon()
    {
        float tmp = .5f;
        while ((1 + tmp) > 1)
        {
            tmp = tmp / 2;
        }
        return tmp;
    }

    public static boolean gte(final double lhs, final double rhs)
    {
        if (Double.compare(lhs, rhs) == 0)
            return true;

        return false;
       // return (Math.abs(lhs - rhs) >= DBL_EPSILON);
    }

    public static boolean eq(final double lhs, final double d2)
    {
        return Double.compare(lhs,d2) == 0;
    }

    public static void main(final String[] args)
    {
        System.out.println(String.format("FLT_EPSILON %.20f",FLT_EPSILON));
        System.out.println(String.format("DBL_EPSILON %.20f",DBL_EPSILON));
        
        System.out.println("eq " + eq(0.00000000000000011102,0.00000000000000011102));
    }
}
