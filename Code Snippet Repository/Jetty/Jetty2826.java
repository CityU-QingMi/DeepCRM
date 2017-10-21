    public boolean onWritePossible()
    {
        boolean wake=false;

        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onWritePossible {}",toStringLocked());
            
            _asyncWritePossible=true;
            if (_state==State.ASYNC_WAIT)
            {
                _state=State.ASYNC_WOKEN;
                wake=true;
            }
        }

        return wake;
    }
