    protected ServerSocketChannel openAcceptChannel() throws IOException
    {
        ServerSocketChannel serverChannel = null;
        if (isInheritChannel())
        {
            Channel channel = System.inheritedChannel();
            if (channel instanceof ServerSocketChannel)
                serverChannel = (ServerSocketChannel)channel;
            else
                LOG.warn("Unable to use System.inheritedChannel() [{}]. Trying a new ServerSocketChannel at {}:{}", channel, getHost(), getPort());
        }

        if (serverChannel == null)
        {
            serverChannel = ServerSocketChannel.open();

            InetSocketAddress bindAddress = getHost() == null ? new InetSocketAddress(getPort()) : new InetSocketAddress(getHost(), getPort());
            serverChannel.socket().setReuseAddress(getReuseAddress());
            serverChannel.socket().bind(bindAddress, getAcceptQueueSize());
        }

        return serverChannel;
    }
