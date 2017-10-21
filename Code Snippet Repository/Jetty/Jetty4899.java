    public static Method findMethod (Class<?> clazz, String methodName, Class<?>[] args, boolean checkInheritance, boolean strictArgs)
    throws NoSuchMethodException
    {
        if (clazz == null)
            throw new NoSuchMethodException("No class");
        if (methodName==null || methodName.trim().equals(""))
            throw new NoSuchMethodException("No method name");
        
        Method method = null;
        Method[] methods = clazz.getDeclaredMethods();
        for (int i=0;i<methods.length && method==null;i++)
        {
            if (methods[i].getName().equals(methodName) && checkParams(methods[i].getParameterTypes(), (args==null?new Class[] {}:args), strictArgs))
            {
                method = methods[i];
            }
            
        }
        if (method!=null)
        {
            return method;
        }
        else if (checkInheritance)
                return findInheritedMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, args, strictArgs);
        else
            throw new NoSuchMethodException("No such method "+methodName+" on class "+clazz.getName());

    }
