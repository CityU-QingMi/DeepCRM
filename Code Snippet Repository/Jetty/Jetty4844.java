    @Override
    protected Class<?> resolveProxyClass(String[] interfaces)
            throws IOException, ClassNotFoundException
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        ClassLoader nonPublicLoader = null;
        boolean hasNonPublicInterface = false;

        // define proxy in class loader of non-public interface(s), if any
        Class<?>[] classObjs = new Class[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) 
        {
            Class<?> cl = Class.forName(interfaces[i], false, loader);
            if ((cl.getModifiers() & Modifier.PUBLIC) == 0) 
            {
                if (hasNonPublicInterface) 
                {
                    if (nonPublicLoader != cl.getClassLoader()) 
                    {
                        throw new IllegalAccessError(
                                "conflicting non-public interface class loaders");
                    }
                } 
                else 
                {
                    nonPublicLoader = cl.getClassLoader();
                    hasNonPublicInterface = true;
                }
            }
            classObjs[i] = cl;
        }
        try 
        {
            return Proxy.getProxyClass(hasNonPublicInterface ? nonPublicLoader : loader,classObjs);
        } 
        catch (IllegalArgumentException e) 
        {
            throw new ClassNotFoundException(null, e);
        }    
    }
