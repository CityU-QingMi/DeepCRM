    public void stop()
    {
        try
        {
            if (_server.isRunning())
            {
                _server.stop();
            }
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
