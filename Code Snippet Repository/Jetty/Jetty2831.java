    protected void recycle()
    {
        cancelTimeout();
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("recycle {}",toStringLocked());
            
            switch(_state)
            {
                case DISPATCHED:
                case ASYNC_IO:
                    throw new IllegalStateException(getStatusStringLocked());
                case UPGRADED:
                    return;
                default:
                    break;
            }
            _asyncListeners=null;
            _state=State.IDLE;
            _async=Async.NOT_ASYNC;
            _initial=true;
            _asyncRead=AsyncRead.IDLE;
            _asyncWritePossible=false;
            _timeoutMs=DEFAULT_TIMEOUT;
            _event=null;
        }
    }
