    public boolean onFail(Throwable cause)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onFail {} {}",this,cause);
        Callback callback = _interested.get();
        if (callback != null && _interested.compareAndSet(callback, null))
        {
            callback.failed(cause);
            return true;
        }
        return false;
    }
