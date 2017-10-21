    public void connect()
        throws IOException
    {
        if (_serviceConnection == null)
        {
            if (_serviceUrl == null)
                openLoopbackConnection();
            else
                openServerConnection(_serviceUrl);
        }
    }
