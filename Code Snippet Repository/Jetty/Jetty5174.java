    @Override
    protected void doStart() throws Exception
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try
        {
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
            prevent(getClass().getClassLoader());
            super.doStart();
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(loader);
        }
    }
