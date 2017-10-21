    private synchronized void set(String url)
    {
        _serverUrl = url;

        if (_serviceConnection != null)
        {
            _serviceConnection.disconnect();
            _serviceConnection = null;
        }
    }
