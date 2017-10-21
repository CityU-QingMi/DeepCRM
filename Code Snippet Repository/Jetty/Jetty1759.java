    @Before
    public void startManager() throws Exception
    {
        _writeCount = 1;
        _lastEndPoint = null;
        _lastEndPointLatch = new CountDownLatch(1);
        _connector = ServerSocketChannel.open();
        _connector.socket().bind(null);
        _scheduler.start();
        _threadPool.start();
        _manager.start();
    }
