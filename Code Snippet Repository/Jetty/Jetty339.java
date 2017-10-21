    private void terminateRequest(HttpExchange exchange)
    {
        // In abort(), the state is updated before the failure is recorded
        // to avoid to overwrite it, so here we may read a null failure.
        Throwable failure = this.failure;
        if (failure == null)
            failure = new HttpRequestException("Concurrent failure", exchange.getRequest());
        Result result = exchange.terminateRequest();
        terminateRequest(exchange, failure, result);
    }
