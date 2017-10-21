    public static long longValue(Object value) throws NumberFormatException {
        if (value == null)
            return 0L;
        Class c = value.getClass();
        if (c.getSuperclass() == Number.class)
            return ((Number) value).longValue();
        if (c == Boolean.class)
            return (Boolean) value ? 1 : 0;
        if (c == Character.class)
            return (Character) value;
        return Long.parseLong(stringValue(value, true));
    }
