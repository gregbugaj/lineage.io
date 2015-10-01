package io.lineage;

/**
 * @ref GWT project
 * Simple pair class.
 * 
 * @param <A> any type
 * @param <B> any type
 */
public class Pair<A, B>
{
    private final A a;

    private final B b;

    public Pair(final A a, final B b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (!(o instanceof Pair<?, ?>))
        {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) o;
        return a.equals(other.a) && b.equals(other.b);
    }

    public A getA()
    {
        return a;
    }

    public B getB()
    {
        return b;
    }

    @Override
    public int hashCode()
    {
        return a.hashCode() * 13 + b.hashCode() * 7;
    }
}
