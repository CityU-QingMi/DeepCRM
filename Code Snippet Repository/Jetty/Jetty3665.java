    @Test(timeout=60000)
    public void testIdleTimeoutAfterSuspend() throws Exception
    {
        _server.stop();
        SuspendHandler _handler = new SuspendHandler();
        SessionHandler session = new SessionHandler();
        session.setHandler(_handler);
        _server.setHandler(session);
        _server.start();

        _handler.setSuspendFor(100);
        _handler.setResumeAfter(25);
        Assert.assertTrue(process(null).toUpperCase(Locale.ENGLISH).contains("RESUMED"));
    }
