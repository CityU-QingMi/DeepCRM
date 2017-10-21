    public final static Number mod(final Object obj0, final Object obj1) {
        if (obj0 == null && obj1 == null) {
            return new Long(0);
        }

        final ELArithmetic delegate;
        if (BIGDECIMAL.matches(obj0, obj1))
            delegate = BIGDECIMAL;
        else if (DOUBLE.matches(obj0, obj1))
            delegate = DOUBLE;
        else if (BIGINTEGER.matches(obj0, obj1))
            delegate = BIGINTEGER;
        else
            delegate = LONG;

        Number num0 = delegate.coerce(obj0);
        Number num1 = delegate.coerce(obj1);

        return delegate.mod(num0, num1);
    }
