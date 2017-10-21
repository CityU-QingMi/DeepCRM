    public static boolean isSameSignature (Method methodA, Method methodB)
    {
        if (methodA==null)
            return false;
        if (methodB==null)
            return false;
        
        List<Class<?>> parameterTypesA = Arrays.asList(methodA.getParameterTypes());
        List<Class<?>> parameterTypesB = Arrays.asList(methodB.getParameterTypes());
       
        if (methodA.getName().equals(methodB.getName())
            &&
            parameterTypesA.containsAll(parameterTypesB))
            return true;
        
        return false;
    }
