    protected void doProcessBinding (Node node, App app) throws Exception
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(_server.getParentClassLoaderForWebapps());
        try
        {
            super.processBinding(node,app);
        }
        finally 
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }
