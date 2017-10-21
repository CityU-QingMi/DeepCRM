    protected boolean beginToHeaders(HttpExchange exchange)
    {
        if (!updateRequestState(RequestState.BEGIN, RequestState.TRANSIENT))
            return false;

        Request request = exchange.getRequest();
        if (LOG.isDebugEnabled())
            LOG.debug("Request headers {}{}{}", request, System.lineSeparator(), request.getHeaders().toString().trim());
        RequestNotifier notifier = getHttpChannel().getHttpDestination().getRequestNotifier();
        notifier.notifyHeaders(request);

        if (updateRequestState(RequestState.TRANSIENT, RequestState.HEADERS))
            return true;

        terminateRequest(exchange);
        return false;
    }
