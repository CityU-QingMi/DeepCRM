    protected void unlockCompEnv (WebAppContext wac)
    throws Exception
    {
        if (_key!=null)
        {
            ClassLoader old_loader = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(wac.getClassLoader());

            try
            {
                Context context = new InitialContext();
                Context compCtx = (Context)context.lookup("java:comp");
                compCtx.addToEnvironment("org.eclipse.jetty.jndi.unlock", _key);
            }
            finally
            {
                Thread.currentThread().setContextClassLoader(old_loader);
            }
        }
    }
