    @Override
    protected void doStart() throws Exception
    {
        if (executor == null)
            setExecutor(new QueuedThreadPool());

        if (scheduler == null)
            setScheduler(new ScheduledExecutorScheduler());

        if (bufferPool == null)
            setByteBufferPool(new MappedByteBufferPool());

        if (connectionFactory == null)
        {
            HTTP2ClientConnectionFactory h2 = new HTTP2ClientConnectionFactory();
            setClientConnectionFactory((endPoint, context) ->
            {
                ClientConnectionFactory factory = h2;
                SslContextFactory sslContextFactory = (SslContextFactory)context.get(SslClientConnectionFactory.SSL_CONTEXT_FACTORY_CONTEXT_KEY);
                if (sslContextFactory != null)
                {
                    ALPNClientConnectionFactory alpn = new ALPNClientConnectionFactory(getExecutor(), h2, getProtocols());
                    factory = newSslClientConnectionFactory(sslContextFactory, alpn);
                }
                return factory.newConnection(endPoint, context);
            });
        }

        if (selector == null)
        {
            selector = newSelectorManager();
            addBean(selector);
        }
        selector.setConnectTimeout(getConnectTimeout());

        super.doStart();
    }
