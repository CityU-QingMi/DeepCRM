    protected boolean responseHeaders(HttpExchange exchange)
    {
        out: while (true)
        {
            ResponseState current = responseState.get();
            switch (current)
            {
                case BEGIN:
                case HEADER:
                {
                    if (updateResponseState(current, ResponseState.TRANSIENT))
                        break out;
                    break;
                }
                default:
                {
                    return false;
                }
            }
        }

        HttpResponse response = exchange.getResponse();
        if (LOG.isDebugEnabled())
            LOG.debug("Response headers {}{}{}", response, System.lineSeparator(), response.getHeaders().toString().trim());
        ResponseNotifier notifier = getHttpDestination().getResponseNotifier();
        notifier.notifyHeaders(exchange.getConversation().getResponseListeners(), response);

        Enumeration<String> contentEncodings = response.getHeaders().getValues(HttpHeader.CONTENT_ENCODING.asString(), ",");
        if (contentEncodings != null)
        {
            for (ContentDecoder.Factory factory : getHttpDestination().getHttpClient().getContentDecoderFactories())
            {
                while (contentEncodings.hasMoreElements())
                {
                    if (factory.getEncoding().equalsIgnoreCase(contentEncodings.nextElement()))
                    {
                        this.decoder = factory.newContentDecoder();
                        break;
                    }
                }
            }
        }

        if (updateResponseState(ResponseState.TRANSIENT, ResponseState.HEADERS))
            return true;

        terminateResponse(exchange);
        return false;
    }
