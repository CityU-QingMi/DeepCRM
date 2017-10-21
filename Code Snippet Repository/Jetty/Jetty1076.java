    private void notifyReset(Stream stream, ResetFrame frame)
    {
        final Listener listener = this.listener;
        if (listener == null)
            return;
        try
        {
            listener.onReset(stream, frame);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
