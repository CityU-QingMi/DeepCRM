    protected boolean responseBegin(HttpExchange exchange)
    {
        if (!updateResponseState(ResponseState.IDLE, ResponseState.TRANSIENT))
            return false;

        HttpConversation conversation = exchange.getConversation();
        HttpResponse response = exchange.getResponse();
        // Probe the protocol handlers
        HttpDestination destination = getHttpDestination();
        HttpClient client = destination.getHttpClient();
        ProtocolHandler protocolHandler = client.findProtocolHandler(exchange.getRequest(), response);
        Response.Listener handlerListener = null;
        if (protocolHandler != null)
        {
            handlerListener = protocolHandler.getResponseListener();
            if (LOG.isDebugEnabled())
                LOG.debug("Found protocol handler {}", protocolHandler);
        }
        exchange.getConversation().updateResponseListeners(handlerListener);

        if (LOG.isDebugEnabled())
            LOG.debug("Response begin {}", response);
        ResponseNotifier notifier = destination.getResponseNotifier();
        notifier.notifyBegin(conversation.getResponseListeners(), response);

        if (updateResponseState(ResponseState.TRANSIENT, ResponseState.BEGIN))
            return true;

        terminateResponse(exchange);
        return false;
    }
