    @Override
    public void onError(Throwable cause)
    {
        try
        {
            endpoint.onError(jsrsession,cause);
        }
        catch (Throwable t)
        {
            LOG.warn("Unable to report to onError due to exception",t);
        }
    }
