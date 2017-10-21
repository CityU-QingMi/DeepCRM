    @Override
    protected void doShutdownOutput()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("oshut {}", this);
        try
        {
            _channel.shutdownOutput();
            super.doShutdownOutput();
        }
        catch (IOException e)
        {
            LOG.debug(e);
        }
    }
