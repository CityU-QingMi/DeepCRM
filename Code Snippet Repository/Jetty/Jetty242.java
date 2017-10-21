    public boolean associate(HttpExchange exchange)
    {
        boolean result = false;
        boolean abort = true;
        synchronized (this)
        {
            if (_exchange == null)
            {
                abort = false;
                result = exchange.associate(this);
                if (result)
                    _exchange = exchange;
            }
        }

        if (abort)
            exchange.getRequest().abort(new UnsupportedOperationException("Pipelined requests not supported"));

        if (LOG.isDebugEnabled())
            LOG.debug("{} associated {} to {}", exchange, result, this);

        return result;
    }
