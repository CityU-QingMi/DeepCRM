    public boolean onContentAdded()
    {
        boolean woken=false;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onContentAdded {}",toStringLocked());
            
            switch(_asyncRead)
            {
                case IDLE:
                case READY:
                    break;
                    
                case PRODUCING:
                    _asyncRead=AsyncRead.READY;
                    break;
                    
                case REGISTER:
                case REGISTERED:
                    _asyncRead=AsyncRead.READY;
                    if (_state==State.ASYNC_WAIT)
                    {
                        woken=true;
                        _state=State.ASYNC_WOKEN;
                    }
                    break;

                case POSSIBLE:
                    throw new IllegalStateException(toStringLocked());
            }
        }
        return woken;
    }
