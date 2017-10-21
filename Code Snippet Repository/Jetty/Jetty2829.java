    public void complete()
    {

        // just like resume, except don't set _dispatched=true;
        boolean handle=false;
        AsyncContextEvent event;
        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("complete {}",toStringLocked());
            
            boolean started=false;
            event=_event;
            
            switch(_async)
            {
                case STARTED:
                    started=true;
                    break;
                case EXPIRING:
                case ERRORING:
                case ERRORED:
                    break;
                case COMPLETE:
                    return;
                default:
                    throw new IllegalStateException(this.getStatusStringLocked());
            }
            _async=Async.COMPLETE;
            
            if (started && _state==State.ASYNC_WAIT)
            {
                handle=true;
                _state=State.ASYNC_WOKEN;
            }
        }

        cancelTimeout(event);
        if (handle)
            runInContext(event,_channel);
    }
