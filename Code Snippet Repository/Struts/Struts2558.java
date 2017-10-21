    public final static Number divide(final Object obj0, final Object obj1) {
        if (obj0 == null && obj1 == null) {
            return ZERO;
        }

        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1))
            delegate = BIGDECIMAL;
        else if (BIGINTEGER.matches(obj0, obj1))
            delegate = BIGDECIMAL;
        else
            delegate = DOUBLE;

        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);

        return delegate.divide(num0, num1);
    }
