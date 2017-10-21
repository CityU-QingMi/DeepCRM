    protected void notifyCallbackSuccess(WriteCallback callback)
    {
        try
        {
            if (callback != null)
                callback.writeSuccess();
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Exception while notifying success of callback " + callback,x);
        }
    }
