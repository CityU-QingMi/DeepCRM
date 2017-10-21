    public void open(ServerSocketChannel acceptChannel) throws IOException
    {
        if (isStarted())
            throw new IllegalStateException(getState());
        updateBean(_acceptChannel,acceptChannel);
        _acceptChannel = acceptChannel;
        _localPort = _acceptChannel.socket().getLocalPort();
        if (_localPort <= 0)
            throw new IOException("Server channel not bound");
    }
