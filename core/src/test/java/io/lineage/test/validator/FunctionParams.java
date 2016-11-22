package io.lineage.test.validator;

public class FunctionParams
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public FunctionParams()
    {
    }

    public FunctionParams(final int a, final int b, final int c, final int d, final int e, final int f, final int g, final int h)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    @Override
    public String toString()
    {
        return String.format("FunctionParams[ %s, %s, %s, %s, %s, %s, %s, %s ]",a,b,c,d,e,f,g,h);
    }
}
