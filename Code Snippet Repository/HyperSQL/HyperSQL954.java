    public static Object toAdjustedArray(Object source, Object addition,
                                         int colindex, int adjust) {

        int newsize = Array.getLength(source) + adjust;
        Object newarray =
            Array.newInstance(source.getClass().getComponentType(), newsize);

        copyAdjustArray(source, newarray, addition, colindex, adjust);

        return newarray;
    }
