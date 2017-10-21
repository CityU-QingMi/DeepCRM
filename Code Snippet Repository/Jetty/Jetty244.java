    public boolean abort(HttpExchange exchange, Throwable requestFailure, Throwable responseFailure)
    {
        boolean requestAborted = false;
        if (requestFailure != null)
            requestAborted = getHttpSender().abort(exchange, requestFailure);

        boolean responseAborted = false;
        if (responseFailure != null)
            responseAborted = abortResponse(exchange, responseFailure);

        return requestAborted || responseAborted;
    }
