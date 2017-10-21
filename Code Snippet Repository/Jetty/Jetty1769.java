    @Before
    public void startManager() throws Exception
    {
        _testFill=true;
        _writeCallback=null;
        _lastEndp=null;
        _connector = ServerSocketChannel.open();
        _connector.socket().bind(null);
        _threadPool.start();
        _scheduler.start();
        _manager.start();
        __sslCtxFactory.setRenegotiationAllowed(true);
        __sslCtxFactory.setRenegotiationLimit(-1);

    }
