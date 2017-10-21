    protected boolean responseSuccess(HttpExchange exchange)
    {
        // Mark atomically the response as completed, with respect
        // to concurrency between response success and response failure.
        if (!exchange.responseComplete(null))
            return false;

        responseState.set(ResponseState.IDLE);

        // Reset to be ready for another response.
        reset();

        HttpResponse response = exchange.getResponse();
        if (LOG.isDebugEnabled())
            LOG.debug("Response success {}", response);
        List<Response.ResponseListener> listeners = exchange.getConversation().getResponseListeners();
        ResponseNotifier notifier = getHttpDestination().getResponseNotifier();
        notifier.notifySuccess(listeners, response);

        // Special case for 100 Continue that cannot
        // be handled by the ContinueProtocolHandler.
        if (exchange.getResponse().getStatus() == HttpStatus.CONTINUE_100)
            return true;

        // Mark atomically the response as terminated, with
        // respect to concurrency between request and response.
        Result result = exchange.terminateResponse();
        terminateResponse(exchange, result);

        return true;
    }
