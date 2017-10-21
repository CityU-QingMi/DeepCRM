    protected void notifyPing(PingFrame frame)
    {
        try
        {
            listener.onPing(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
