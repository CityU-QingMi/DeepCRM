    public boolean tryRegister(Callback callback)
    {
        if (callback == null)
            throw new IllegalArgumentException();

        if (!_interested.compareAndSet(null, callback))
            return false;

        if (LOG.isDebugEnabled())
            LOG.debug("interested {}",this);
        
        try
        {
            needsFillInterest();
        }
        catch (Throwable e)
        {
            onFail(e);
        }
        
        return true;
    }
