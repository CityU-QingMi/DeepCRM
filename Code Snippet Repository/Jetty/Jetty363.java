    private void notifyBegin(Request.BeginListener listener, Request request)
    {
        try
        {
            listener.onBegin(request);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
