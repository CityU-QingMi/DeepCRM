    @Test(expected = TimeoutException.class)
    public void testConnectionTimeout_Concurrent() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        Assert.assertNotNull(ssocket);
        // Intentionally don't upgrade
        // ssocket.upgrade();

        // The attempt to get upgrade response future should throw error
        try
        {
            future.get(3,TimeUnit.SECONDS);
            Assert.fail("Expected ExecutionException -> TimeoutException");
        }
        catch (ExecutionException e)
        {
            // Expected path - java.net.ConnectException ?
            assertExpectedError(e,wsocket,instanceOf(ConnectException.class));
        }
    }
