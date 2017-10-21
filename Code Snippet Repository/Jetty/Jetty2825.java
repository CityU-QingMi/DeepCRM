    public boolean onReadEof()
    {
        boolean woken=false;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onEof {}",toStringLocked());

            // Force read ready so onAllDataRead can be called
            _asyncRead=AsyncRead.READY;
            if (_state==State.ASYNC_WAIT)
            {
                woken=true;
                _state=State.ASYNC_WOKEN;
            }
        }
        return woken;
    }
