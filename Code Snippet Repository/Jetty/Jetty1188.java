    @Override
    public org.eclipse.jetty.io.Connection newConnection(EndPoint endPoint, Map<String, Object> context) throws IOException
    {
        endPoint.setIdleTimeout(getHttpClient().getIdleTimeout());

        ClientConnectionFactory factory = connectionFactory;
        HttpDestinationOverHTTP2 destination = (HttpDestinationOverHTTP2)context.get(HTTP_DESTINATION_CONTEXT_KEY);
        ProxyConfiguration.Proxy proxy = destination.getProxy();
        boolean ssl = proxy == null ? HttpScheme.HTTPS.is(destination.getScheme()) : proxy.isSecure();
        if (ssl && isUseALPN())
            factory = new ALPNClientConnectionFactory(client.getExecutor(), factory, client.getProtocols());
        return factory.newConnection(endPoint, context);
    }
