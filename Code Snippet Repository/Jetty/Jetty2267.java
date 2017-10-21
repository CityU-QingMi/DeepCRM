    public void validate(Class<?> clazz, Method method)
    {        

        if (method.getExceptionTypes().length > 0)
            throw new IllegalArgumentException(clazz.getName()+"."+method.getName()+ " cannot not throw a checked exception");
        
        if (!method.getReturnType().equals(Void.TYPE))
            throw new IllegalArgumentException(clazz.getName()+"."+method.getName()+ " cannot not have a return type");
        
        if (Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException(clazz.getName()+"."+method.getName()+ " cannot be static");
        
    }
