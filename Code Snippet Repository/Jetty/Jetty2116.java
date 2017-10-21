    public ContextHandler createContextHandler(App app) throws Exception
    {
        if (app == null)
            return null;
        if (!(app instanceof OSGiApp))
            throw new IllegalStateException(app+" is not a BundleApp");
        
        //Create a ContextHandler suitable to deploy in OSGi
        ContextHandler h = ((OSGiApp)app).createContextHandler();           
        return h;
    }
