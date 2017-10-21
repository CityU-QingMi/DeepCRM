    protected void notifyHeaders(HeadersFrame frame)
    {
        try
        {
            listener.onHeaders(frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
