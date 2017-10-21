    protected void lockCompEnv (WebAppContext wac)
    throws Exception
    {
        ClassLoader old_loader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(wac.getClassLoader());
        try
        {
            Random random = new Random ();
            _key = new Integer(random.nextInt());
            Context context = new InitialContext();
            Context compCtx = (Context)context.lookup("java:comp");
            compCtx.addToEnvironment("org.eclipse.jetty.jndi.lock", _key);
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old_loader);
        }
    }
