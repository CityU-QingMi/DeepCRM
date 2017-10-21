    @Override
    public void accept(int acceptorID) throws IOException
    {
        LOG.debug("Blocking UnixSocket accept used.  Might not be able to be interrupted!");
        UnixServerSocketChannel serverChannel = _acceptChannel;
        if (serverChannel != null && serverChannel.isOpen())
        {
            LOG.debug("accept {}",serverChannel);
            UnixSocketChannel channel = serverChannel.accept();
            LOG.debug("accepted {}",channel);
            accepted(channel);
        }
    }
