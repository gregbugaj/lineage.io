package io.lineage;

//package org.gwtoolbox.commons.util.client;

/**
 * @author Uri Boness
 */
public class Assert
{

    public static void isEqual(final Object o1, final Object o2) throws IllegalArgumentException
    {
        isEqual(o1,o2,"Assertion failed: two given objects are expected to be equal");
    }

    public static void isEqual(final Object o1, final Object o2, final String message) throws IllegalArgumentException
    {
        if (!o1.equals(o2))
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isSame(final Object o1, final Object o2)
    {
        isSame(o1,o2,"Assertion failed: two give object are expected to be the same object");
    }

    public static void isSame(final Object o1, final Object o2, final String message)
    {
        if (o1 != o2)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(final Object object)
    {
        notNull(object,"Assertion failed: give object cannot be null");
    }

    public static void notNull(final Object object, final String message)
    {
        if (object == null)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(final boolean expression)
    {
        isTrue(expression,"Assertion failed: give expression is expected to be true");
    }

    public static void isTrue(final boolean expression, final String message)
    {
        if (!expression)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(final boolean expression)
    {
        isFalse(expression,"Assertion failed: give expression is expected to be false");
    }

    public static void isFalse(final boolean expression, final String message)
    {
        isTrue(!expression,message);
    }

    public static void state(final boolean expression) throws IllegalStateException
    {
        state(expression,"Assertion failed: illegal state");
    }

    public static void state(final boolean expression, final String message) throws IllegalStateException
    {
        if (!expression)
        {
            throw new IllegalStateException(message);
        }
    }

}
