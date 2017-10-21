    protected boolean someToSuccess(HttpExchange exchange)
    {
        RequestState current = requestState.get();
        switch (current)
        {
            case COMMIT:
            case CONTENT:
            {
                // Mark atomically the request as completed, with respect
                // to concurrency between request success and request failure.
                if (!exchange.requestComplete(null))
                    return false;

                requestState.set(RequestState.QUEUED);

                // Reset to be ready for another request.
                reset();

                Request request = exchange.getRequest();
                if (LOG.isDebugEnabled())
                    LOG.debug("Request success {}", request);
                HttpDestination destination = getHttpChannel().getHttpDestination();
                destination.getRequestNotifier().notifySuccess(exchange.getRequest());

                // Mark atomically the request as terminated, with
                // respect to concurrency between request and response.
                Result result = exchange.terminateRequest();
                terminateRequest(exchange, null, result);
                return true;
            }
            default:
            {
                return false;
            }
        }
    }
