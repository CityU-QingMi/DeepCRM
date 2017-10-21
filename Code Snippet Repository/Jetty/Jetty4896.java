    public static boolean isJavaBeanCompliantSetter (Method method)
    {
        if (method == null)
            return false;
        
        if (method.getReturnType() != Void.TYPE)
            return false;
        
        if (!method.getName().startsWith("set"))
            return false;
        
        if (method.getParameterCount() != 1)
            return false;
        
        return true;
    }
