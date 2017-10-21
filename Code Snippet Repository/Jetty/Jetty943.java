    @Override
    public void stop(int delay)
    {
        cleanUpContexts();
        cleanUpConnectors();

        if (_serverShared) return;

        try
        {
            _server.stop();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
