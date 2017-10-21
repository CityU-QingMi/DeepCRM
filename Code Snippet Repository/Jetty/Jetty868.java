    @Override
    public void noContent()
    {
        try
        {
            listener.onEnd(getRequest());
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Exception while invoking listener " + listener, x);
        }
    }
