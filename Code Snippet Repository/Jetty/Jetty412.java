    @Override
    protected void sendTrailers(HttpExchange exchange, Callback callback)
    {
        try
        {
            new TrailersCallback(callback).iterate();
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(x);
            callback.failed(x);
        }
    }
