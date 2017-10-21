    protected boolean someToContent(HttpExchange exchange, ByteBuffer content)
    {
        RequestState current = requestState.get();
        switch (current)
        {
            case COMMIT:
            case CONTENT:
            {
                if (!updateRequestState(current, RequestState.TRANSIENT))
                    return false;

                Request request = exchange.getRequest();
                if (LOG.isDebugEnabled())
                    LOG.debug("Request content {}{}{}", request, System.lineSeparator(), BufferUtil.toDetailString(content));
                RequestNotifier notifier = getHttpChannel().getHttpDestination().getRequestNotifier();
                notifier.notifyContent(request, content);

                if (updateRequestState(RequestState.TRANSIENT, RequestState.CONTENT))
                    return true;

                terminateRequest(exchange);
                return false;
            }
            default:
            {
                return false;
            }
        }
    }
