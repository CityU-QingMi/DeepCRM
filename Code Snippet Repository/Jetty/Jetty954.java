    public void connect(SslContextFactory sslContextFactory, InetSocketAddress address, Session.Listener listener, Promise<Session> promise, Map<String, Object> context)
    {
        try
        {
            SocketChannel channel = SocketChannel.open();
            configure(channel);
            channel.configureBlocking(false);
            context = contextFrom(sslContextFactory, address, listener, promise, context);
            if (channel.connect(address))
                selector.accept(channel, context);
            else
                selector.connect(channel, context);
        }
        catch (Throwable x)
        {
            promise.failed(x);
        }
    }
