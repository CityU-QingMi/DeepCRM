    @After
    public void tearDown()
        throws Exception
    {
        stopClient();

        _mBeanContainer.destroy();
        
        if (_server != null)
        {
            _server.stop();
            _server = null;
        }
    }
