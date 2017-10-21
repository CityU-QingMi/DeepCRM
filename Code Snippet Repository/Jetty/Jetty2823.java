    public boolean onReadReady()
    {
        boolean woken=false;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onReadReady {}",toStringLocked());

            switch(_asyncRead)
            {
                case IDLE:
                    _asyncRead=AsyncRead.READY;
                    if (_state==State.ASYNC_WAIT)
                    {
                        woken=true;
                        _state=State.ASYNC_WOKEN;
                    }
                    break;

                default:
                    throw new IllegalStateException(toStringLocked());
            }
        }
        return woken;
    }
