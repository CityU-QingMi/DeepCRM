    public void onReadUnready()
    {
        boolean interested=false;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("onReadUnready {}",toStringLocked());
            
            switch(_asyncRead)
            {
                case IDLE:
                case READY:
                    if (_state==State.ASYNC_WAIT)
                    {
                        interested=true;
                        _asyncRead=AsyncRead.REGISTERED;
                    }
                    else
                    {
                        _asyncRead=AsyncRead.REGISTER;
                    }
                    break;

                case REGISTER:
                case REGISTERED:
                case POSSIBLE:
                case PRODUCING:
                    break;
            }
        }

        if (interested)
            _channel.onAsyncWaitForContent();
    }
