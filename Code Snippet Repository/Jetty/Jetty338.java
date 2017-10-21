    protected boolean anyToFailure(Throwable failure)
    {
        HttpExchange exchange = getHttpExchange();
        if (exchange == null)
            return false;

        // Mark atomically the request as completed, with respect
        // to concurrency between request success and request failure.
        if (exchange.requestComplete(failure))
            return abort(exchange, failure);

        return false;
    }
