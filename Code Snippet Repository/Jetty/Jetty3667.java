    @Test(timeout=60000)
    public void testIdleTimeoutAfterComplete() throws Exception
    {
        SuspendHandler _handler = new SuspendHandler();
        _server.stop();
        SessionHandler session = new SessionHandler();
        session.setHandler(_handler);
        _server.setHandler(session);
        _server.start();

        _handler.setSuspendFor(100);
        _handler.setCompleteAfter(25);
        Assert.assertTrue(process(null).toUpperCase(Locale.ENGLISH).contains("COMPLETED"));
    }
