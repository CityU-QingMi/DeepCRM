    @Override
    protected void doShutdownOutput()
    {
        try
        {
            if (!_socket.isOutputShutdown())
                _socket.shutdownOutput();
        }
        catch (IOException e)
        {
            LOG.debug(e);
        }
    }
