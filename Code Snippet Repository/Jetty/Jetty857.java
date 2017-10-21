    protected void onParam(String name, String value)
    {
        try
        {
            listener.onHeader(getRequest(), new HttpField(name, value));
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Exception while invoking listener " + listener, x);
        }
    }
