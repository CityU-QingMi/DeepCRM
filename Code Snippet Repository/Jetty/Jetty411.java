    @Override
    protected void sendHeaders(HttpExchange exchange, HttpContent content, Callback callback)
    {
        try
        {
            new HeadersCallback(exchange, content, callback).iterate();
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(x);
            callback.failed(x);
        }
    }
