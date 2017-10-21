    private synchronized MBeanServerConnection get()
        throws IOException
    {
        if (_serviceConnection == null)
        {
            _serviceConnection = new ServiceConnection(_serverUrl);
            _serviceConnection.connect();
        }
        
        return _serviceConnection.getConnection();
    }
