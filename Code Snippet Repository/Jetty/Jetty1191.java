    private void abort(Throwable failure)
    {
        for (HttpChannel channel : channels)
        {
            HttpExchange exchange = channel.getHttpExchange();
            if (exchange != null)
                exchange.getRequest().abort(failure);
        }
        channels.clear();
    }
