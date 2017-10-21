    public static boolean isTypeCompatible (Class<?> formalType, Class<?> actualType, boolean strict)
    {
        if (formalType==null)
            return actualType==null;
        if (actualType==null)
            return false;
        
        if (strict)
            return formalType.equals(actualType);
        else
            return formalType.isAssignableFrom(actualType);
    }
