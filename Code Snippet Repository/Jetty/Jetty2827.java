    public void startAsync(AsyncContextEvent event)
    {
        final List<AsyncListener> lastAsyncListeners;

        try(Locker.Lock lock= _locker.lock())
        {
            if (LOG.isDebugEnabled())
                LOG.debug("startAsync {}",toStringLocked());
            if (_state!=State.DISPATCHED || _async!=Async.NOT_ASYNC)
                throw new IllegalStateException(this.getStatusStringLocked());

            _async=Async.STARTED;
            _event=event;
            lastAsyncListeners=_asyncListeners;
            _asyncListeners=null;            
        }

        if (lastAsyncListeners!=null)
        {
            Runnable callback=new Runnable()
            {
                @Override
                public void run()
                {
                    for (AsyncListener listener : lastAsyncListeners)
                    {
                        try
                        {
                            listener.onStartAsync(event);
                        }
                        catch(Throwable e)
                        {
                            // TODO Async Dispatch Error
                            LOG.warn(e);
                        }
                    }
                }
                @Override
                public String toString()
                {
                    return "startAsync";
                }
            };
                  
            runInContext(event,callback);
        }
    }
