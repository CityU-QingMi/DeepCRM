    public void accept(SslContextFactory sslContextFactory, SocketChannel channel, Session.Listener listener, Promise<Session> promise)
    {
        try
        {
            if (!channel.isConnected())
                throw new IllegalStateException("SocketChannel must be connected");
            channel.configureBlocking(false);
            Map<String, Object> context = contextFrom(sslContextFactory, (InetSocketAddress)channel.getRemoteAddress(), listener, promise, null);
            selector.accept(channel, context);
        }
        catch (Throwable x)
        {
            promise.failed(x);
        }
    }
