    private void notifyQueued(Request.QueuedListener listener, Request request)
    {
        try
        {
            listener.onQueued(request);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
