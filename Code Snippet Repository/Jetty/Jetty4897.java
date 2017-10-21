    protected static Method findInheritedMethod (Package pack, Class<?> clazz, String methodName, Class<?>[] args, boolean strictArgs)
    throws NoSuchMethodException
    {
        if (clazz==null)
            throw new NoSuchMethodException("No class");
        if (methodName==null)
            throw new NoSuchMethodException("No method name");
        
        Method method = null;
        Method[] methods = clazz.getDeclaredMethods();
        for (int i=0;i<methods.length && method==null;i++)
        {
            if (methods[i].getName().equals(methodName) 
                    && isInheritable(pack,methods[i])
                    && checkParams(methods[i].getParameterTypes(), args, strictArgs))
                method = methods[i];
        }
        if (method!=null)
        {
            return method;
        }
        else
            return findInheritedMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, args, strictArgs);
    }
