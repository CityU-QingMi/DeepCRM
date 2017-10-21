    @Override
    public void start()
    {
        if (_serverShared) return;

        try
        {
            _server.start();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
