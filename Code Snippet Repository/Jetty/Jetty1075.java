    private void notifyData(Stream stream, DataFrame frame, Callback callback)
    {
        final Listener listener = this.listener;
        if (listener == null)
            return;
        try
        {
            listener.onData(stream, frame, callback);
        }
        catch (Throwable x)
        {
            LOG.info("Failure while notifying listener " + listener, x);
        }
    }
