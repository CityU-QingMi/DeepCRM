    @Override
    public void setExecutor(Executor executor)
    {
        if (executor == null)
            throw new IllegalArgumentException("missing required 'executor' argument");
        ThreadPool threadPool = _server.getThreadPool();
        if (threadPool instanceof DelegatingThreadPool)
            ((DelegatingThreadPool)_server.getThreadPool()).setExecutor(executor);
        else
            throw new UnsupportedOperationException("!DelegatingThreadPool");
    }
