    protected void notifyData(DataFrame frame)
    {
        try
        {
            listener.onData(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
