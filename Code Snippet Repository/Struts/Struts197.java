    public static double doubleValue(Object value) throws NumberFormatException {
        if (value == null)
            return 0.0;
        Class c = value.getClass();
        if (c.getSuperclass() == Number.class)
            return ((Number) value).doubleValue();
        if (c == Boolean.class)
            return (Boolean) value ? 1 : 0;
        if (c == Character.class)
            return (Character) value;
        String s = stringValue(value, true);

        return (s.length() == 0) ? 0.0 : Double.parseDouble(s);
/**/
/**/
/**/
        // return Double.valueOf( value.toString() ).doubleValue();
    }
