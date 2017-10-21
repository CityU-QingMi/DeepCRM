    public void open() throws IOException
    {
        if (_acceptChannel == null)
        {
            File file = new File(_unixSocket);
            file.deleteOnExit();
            SocketAddress bindAddress = new UnixSocketAddress(file);
            UnixServerSocketChannel serverChannel = UnixServerSocketChannel.open();

            serverChannel.configureBlocking(getAcceptors()>0);
            serverChannel.socket().bind(bindAddress, getAcceptQueueSize());
            addBean(serverChannel);

            LOG.debug("opened {}",serverChannel);
            _acceptChannel = serverChannel;
        }
    }
