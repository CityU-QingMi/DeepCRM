    public void fillable()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("fillable {}",this);
        Callback callback = _interested.get();
        if (callback != null && _interested.compareAndSet(callback, null))
            callback.succeeded();
        else if (LOG.isDebugEnabled())
            LOG.debug("{} lost race {}",this,callback);
    }
