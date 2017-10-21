    private void terminateRequest(HttpExchange exchange, Throwable failure, Result result)
    {
        Request request = exchange.getRequest();

        if (LOG.isDebugEnabled())
            LOG.debug("Terminating request {}", request);

        if (result == null)
        {
            if (failure != null)
            {
                if (exchange.responseComplete(failure))
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Response failure from request {} {}", request, exchange);
                    getHttpChannel().abortResponse(exchange, failure);
                }
            }
        }
        else
        {
            result = channel.exchangeTerminating(exchange, result);
            HttpDestination destination = getHttpChannel().getHttpDestination();
            boolean ordered = destination.getHttpClient().isStrictEventOrdering();
            if (!ordered)
                channel.exchangeTerminated(exchange, result);
            if (LOG.isDebugEnabled())
                LOG.debug("Request/Response {}: {}", failure == null ? "succeeded" : "failed", result);
            HttpConversation conversation = exchange.getConversation();
            destination.getResponseNotifier().notifyComplete(conversation.getResponseListeners(), result);
            if (ordered)
                channel.exchangeTerminated(exchange, result);
        }
    }
