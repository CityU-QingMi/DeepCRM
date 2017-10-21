    protected void createEnvContext (WebAppContext wac)
    throws NamingException
    {
        ClassLoader old_loader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(wac.getClassLoader());
        ContextFactory.associateClassLoader(wac.getClassLoader());
        try
        {
            Context context = new InitialContext();
            Context compCtx =  (Context)context.lookup ("java:comp");
            compCtx.createSubcontext("env");
        }
        finally
        {
            ContextFactory.disassociateClassLoader();
            Thread.currentThread().setContextClassLoader(old_loader);
        }
    }
