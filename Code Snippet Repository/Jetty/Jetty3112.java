    @Override
    public Future<Void> shutdown()
    {
        FutureCallback shutdown=new FutureCallback(false);
        _shutdown.compareAndSet(null,shutdown);
        shutdown=_shutdown.get();
        if (_dispatchedStats.getCurrent()==0)
            shutdown.succeeded();
        return shutdown;
    }
