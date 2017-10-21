    public boolean abort(HttpExchange exchange, Throwable failure)
    {
        // Update the state to avoid more request processing.
        boolean terminate;
        out: while (true)
        {
            RequestState current = requestState.get();
            switch (current)
            {
                case FAILURE:
                {
                    return false;
                }
                default:
                {
                    if (updateRequestState(current, RequestState.FAILURE))
                    {
                        terminate = current != RequestState.TRANSIENT;
                        break out;
                    }
                    break;
                }
            }
        }

        this.failure = failure;

        dispose();

        Request request = exchange.getRequest();
        if (LOG.isDebugEnabled())
            LOG.debug("Request failure {} {} on {}: {}", request, exchange, getHttpChannel(), failure);
        HttpDestination destination = getHttpChannel().getHttpDestination();
        destination.getRequestNotifier().notifyFailure(request, failure);

        if (terminate)
        {
            // Mark atomically the request as terminated, with
            // respect to concurrency between request and response.
            Result result = exchange.terminateRequest();
            terminateRequest(exchange, failure, result);
        }
        else
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Concurrent failure: request termination skipped, performed by helpers");
        }

        return true;
    }
