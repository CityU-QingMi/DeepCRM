    protected void onParams()
    {
        try
        {
            listener.onHeaders(getRequest());
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Exception while invoking listener " + listener, x);
        }
    }
