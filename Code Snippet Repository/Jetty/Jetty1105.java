    protected void notifyWindowUpdate(WindowUpdateFrame frame)
    {
        try
        {
            listener.onWindowUpdate(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
