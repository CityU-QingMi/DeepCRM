    protected boolean responseFailure(Throwable failure)
    {
        HttpExchange exchange = getHttpExchange();
        // In case of a response error, the failure has already been notified
        // and it is possible that a further attempt to read in the receive
        // loop throws an exception that reenters here but without exchange;
        // or, the server could just have timed out the connection.
        if (exchange == null)
            return false;

        // Mark atomically the response as completed, with respect
        // to concurrency between response success and response failure.
        if (exchange.responseComplete(failure))
            return abort(exchange, failure);

        return false;
    }
