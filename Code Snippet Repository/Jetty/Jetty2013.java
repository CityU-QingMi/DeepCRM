    @Override
    protected void doStop() throws Exception
    {
        super.doStop();
        if (_client != null)
        {
            _client.shutdown();
            _client = null;
        }
    }
