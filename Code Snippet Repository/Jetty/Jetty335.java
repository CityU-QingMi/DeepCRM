    protected boolean headersToCommit(HttpExchange exchange)
    {
        if (!updateRequestState(RequestState.HEADERS, RequestState.TRANSIENT))
            return false;

        Request request = exchange.getRequest();
        if (LOG.isDebugEnabled())
            LOG.debug("Request committed {}", request);
        RequestNotifier notifier = getHttpChannel().getHttpDestination().getRequestNotifier();
        notifier.notifyCommit(request);

        if (updateRequestState(RequestState.TRANSIENT, RequestState.COMMIT))
            return true;

        terminateRequest(exchange);
        return false;
    }
