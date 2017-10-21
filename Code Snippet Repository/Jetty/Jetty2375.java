    protected void connectToServer(HttpServletRequest request, String host, int port, Promise<SocketChannel> promise)
    {
        SocketChannel channel = null;
        try
        {
            channel = SocketChannel.open();
            channel.socket().setTcpNoDelay(true);
            channel.configureBlocking(false);
            InetSocketAddress address = newConnectAddress(host, port);
            channel.connect(address);
            promise.succeeded(channel);
        }
        catch (Throwable x)
        {
            close(channel);
            promise.failed(x);
        }
    }
