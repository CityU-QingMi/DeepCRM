    public boolean onReadPossible()
    {
        boolean woken=false;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onReadPossible {}",toStringLocked());

            switch(_asyncRead)
            {
                case REGISTERED:
                    _asyncRead=AsyncRead.POSSIBLE;
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
