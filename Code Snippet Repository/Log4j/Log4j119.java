    ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5,
            final Object p6, final Object p7) {
        params[0] = p0;
        params[1] = p1;
        params[2] = p2;
        params[3] = p3;
        params[4] = p4;
        params[5] = p5;
        params[6] = p6;
        params[7] = p7;
        init(messagePattern, 8, params);
        return this;
    }
