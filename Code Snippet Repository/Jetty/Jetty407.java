    @Override
    public boolean content(ByteBuffer buffer)
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange == null)
            return false;

        CompletableCallback callback = new CompletableCallback()
        {
            @Override
            public void resume()
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Content consumed asynchronously, resuming processing");
                process();
            }

            public void abort(Throwable x)
            {
                failAndClose(x);
            }
        };
        // Do not short circuit these calls.
        boolean proceed = responseContent(exchange, buffer, callback);
        boolean async = callback.tryComplete();
        return !proceed || async;
    }
