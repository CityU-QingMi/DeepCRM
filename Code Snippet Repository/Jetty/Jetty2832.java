    public void upgrade()
    {
        cancelTimeout();
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("upgrade {}",toStringLocked());
            
            switch(_state)
            {
                case IDLE:
                case COMPLETED:
                    break;
                default:
                    throw new IllegalStateException(getStatusStringLocked());
            }
            _asyncListeners=null;
            _state=State.UPGRADED;
            _async=Async.NOT_ASYNC;
            _initial=true;
            _asyncRead=AsyncRead.IDLE;
            _asyncWritePossible=false;
            _timeoutMs=DEFAULT_TIMEOUT;
            _event=null;
        }
    }
