    protected void abort(Throwable failure)
    {
        for (HttpChannelOverFCGI channel : channels.values())
        {
            HttpExchange exchange = channel.getHttpExchange();
            if (exchange != null)
                exchange.getRequest().abort(failure);
        }
        channels.clear();
    }
