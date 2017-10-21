    public void connect() throws IOException
    {
        if (serviceConnection == null)
        {
            if (serviceUrl == null)
            {
                openLoopbackConnection();
            }
            else
            {
                openServerConnection(serviceUrl);
            }
        }
    }
