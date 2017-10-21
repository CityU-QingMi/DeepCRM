    @Override
    public final void start() throws Exception
    {
        synchronized (_lock)
        {
            try
            {
                if (_state == __STARTED || _state == __STARTING)
                    return;
                setStarting();
                doStart();
                setStarted();
            }
            catch (Throwable e)
            {
                setFailed(e);
                throw e;
            }
        }
    }
