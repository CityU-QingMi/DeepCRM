    @Before
    public void setUp() throws Exception
    {
        _open=true;
        _expired=null;
        _timer=new TimerScheduler();
        _timer.start();
        _timeout=new IdleTimeout(_timer)
        {
            @Override
            protected void onIdleExpired(TimeoutException timeout)
            {
                _expired=timeout;
            }

            @Override
            public boolean isOpen()
            {
                return _open;
            }
        };
        _timeout.setIdleTimeout(1000);
    }
