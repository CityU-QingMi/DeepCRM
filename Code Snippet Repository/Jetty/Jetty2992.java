    @Override
    public void open() throws IOException
    {
        if (_acceptChannel == null)
        {
            _acceptChannel = openAcceptChannel();
            _acceptChannel.configureBlocking(true);
            _localPort = _acceptChannel.socket().getLocalPort();
            if (_localPort <= 0)
                throw new IOException("Server channel not bound");
            addBean(_acceptChannel);
        }
    }
