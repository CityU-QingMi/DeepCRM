    @After
    public void tearDown() throws Exception
    {
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());
        Context ic = new InitialContext();
        Context compCtx =  (Context)ic.lookup ("java:comp");
        compCtx.destroySubcontext("env");
        Thread.currentThread().setContextClassLoader(oldLoader);
    }
