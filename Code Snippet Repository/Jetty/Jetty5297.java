    public SSLContext getSslContext()
    {
        if (!isStarted())
            return _setContext;

        synchronized (this)
        {
            return _factory._context;
        }
    }
