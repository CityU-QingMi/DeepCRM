    private void notifyHeaders(Request.HeadersListener listener, Request request)
    {
        try
        {
            listener.onHeaders(request);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
