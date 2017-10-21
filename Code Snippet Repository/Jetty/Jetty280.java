    @Override
    public String toString()
    {
        synchronized (this)
        {
            return String.format("%s@%x req=%s/%s@%h res=%s/%s@%h",
                    HttpExchange.class.getSimpleName(),
                    hashCode(),
                    requestState, requestFailure, requestFailure,
                    responseState, responseFailure, responseFailure);
        }
    }
