    private int getClassID(final Object value)
    {
        if (value == null)
            return 0;
        
        if (value instanceof Byte || 
            value instanceof Short ||
            value instanceof Integer ||
            value instanceof Long)
            return 1;
            
        if (value instanceof Float ||
            value instanceof Double)
            return 2;
        
        if (value instanceof Boolean)
            return 3;

        return 4; // String
    }
