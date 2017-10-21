    @Override
    public final void stop() throws Exception
    {
        synchronized (_lock)
        {
            try
            {
                if (_state == __STOPPING || _state == __STOPPED)
                    return;
                setStopping();
                doStop();
                setStopped();
            }
            catch (Throwable e)
            {
                setFailed(e);
                throw e;
            }
        }
    }
