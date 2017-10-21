    private boolean notifyIdleTimeout(Stream stream, Throwable failure)
    {
        Listener listener = this.listener;
        if (listener == null)
            return true;
        try
        {
            return listener.onIdleTimeout(stream, failure);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
            return true;
        }
    }
