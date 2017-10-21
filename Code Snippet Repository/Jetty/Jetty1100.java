    protected void notifyReset(ResetFrame frame)
    {
        try
        {
            listener.onReset(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
