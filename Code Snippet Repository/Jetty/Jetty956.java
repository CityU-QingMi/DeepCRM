    private Map<String, Object> contextFrom(SslContextFactory sslContextFactory, InetSocketAddress address, Session.Listener listener, Promise<Session> promise, Map<String, Object> context)
    {
        if (context == null)
            context = new HashMap<>();
        context.put(HTTP2ClientConnectionFactory.CLIENT_CONTEXT_KEY, this);
        context.put(HTTP2ClientConnectionFactory.SESSION_LISTENER_CONTEXT_KEY, listener);
        context.put(HTTP2ClientConnectionFactory.SESSION_PROMISE_CONTEXT_KEY, promise);
        if (sslContextFactory != null)
            context.put(SslClientConnectionFactory.SSL_CONTEXT_FACTORY_CONTEXT_KEY, sslContextFactory);
        context.put(SslClientConnectionFactory.SSL_PEER_HOST_CONTEXT_KEY, address.getHostString());
        context.put(SslClientConnectionFactory.SSL_PEER_PORT_CONTEXT_KEY, address.getPort());
        context.putIfAbsent(ClientConnectionFactory.CONNECTOR_CONTEXT_KEY, this);
        return context;
    }
