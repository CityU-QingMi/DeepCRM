    @Override
    public void close()
    {
        super.close();
        
        ServerSocketChannel serverChannel = _acceptChannel;
        _acceptChannel = null;
        if (serverChannel != null)
        {
            removeBean(serverChannel);

            if (serverChannel.isOpen())
            {
                try
                {
                    serverChannel.close();
                }
                catch (IOException e)
                {
                    LOG.warn(e);
                }
            }
        }
        _localPort = -2;
    }
