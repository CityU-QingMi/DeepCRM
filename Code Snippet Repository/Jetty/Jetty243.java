    public boolean disassociate(HttpExchange exchange)
    {
        boolean result = false;
        synchronized (this)
        {
            HttpExchange existing = _exchange;
            _exchange = null;
            if (existing == exchange)
            {
                existing.disassociate(this);
                result = true;
            }
        }

        if (LOG.isDebugEnabled())
            LOG.debug("{} disassociated {} from {}", exchange, result, this);
        return result;
    }
