    public static boolean containsSameMethodSignature (Method method, Class<?> c, boolean checkPackage)
    {
        if (checkPackage)
        {
            if (!c.getPackage().equals(method.getDeclaringClass().getPackage()))
                return false;
        }
        
        boolean samesig = false;
        Method[] methods = c.getDeclaredMethods();
        for (int i=0; i<methods.length && !samesig; i++)
        {
            if (IntrospectionUtil.isSameSignature(method, methods[i]))
                samesig = true;
        }
        return samesig;
    }
