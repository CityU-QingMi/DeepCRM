    protected boolean queuedToBegin(HttpExchange exchange)
    {
        if (!updateRequestState(RequestState.QUEUED, RequestState.TRANSIENT))
            return false;

        Request request = exchange.getRequest();
        if (LOG.isDebugEnabled())
            LOG.debug("Request begin {}", request);
        RequestNotifier notifier = getHttpChannel().getHttpDestination().getRequestNotifier();
        notifier.notifyBegin(request);

        if (updateRequestState(RequestState.TRANSIENT, RequestState.BEGIN))
            return true;

        terminateRequest(exchange);
        return false;
    }
