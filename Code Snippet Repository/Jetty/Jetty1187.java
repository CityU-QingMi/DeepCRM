    @Override
    public void connect(InetSocketAddress address, Map<String, Object> context)
    {
        client.setConnectTimeout(getHttpClient().getConnectTimeout());

        SessionListenerPromise listenerPromise = new SessionListenerPromise(context);

        HttpDestinationOverHTTP2 destination = (HttpDestinationOverHTTP2)context.get(HTTP_DESTINATION_CONTEXT_KEY);
        SslContextFactory sslContextFactory = null;
        if (HttpScheme.HTTPS.is(destination.getScheme()))
            sslContextFactory = getHttpClient().getSslContextFactory();

        client.connect(sslContextFactory, address, listenerPromise, listenerPromise, context);
    }
