    public static boolean booleanValue(Object value) {
        if (value == null)
            return false;
        Class c = value.getClass();
        if (c == Boolean.class)
            return (Boolean) value;
        // if ( c == String.class )
        // return ((String)value).length() > 0;
        if (c == Character.class)
            return (Character) value != 0;
        if (value instanceof Number)
            return ((Number) value).doubleValue() != 0;
        return true; // non-null
    }
