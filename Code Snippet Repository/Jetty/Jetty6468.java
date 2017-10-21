    @Test
    @Slow
    public void testClientSlowToSend() throws Exception
    {
        JettyTrackingSocket tsocket = new JettyTrackingSocket();
        client.getPolicy().setIdleTimeout(60000);

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(tsocket, wsUri);

        IBlockheadServerConnection sconnection = server.accept();
        sconnection.setSoTimeout(60000);
        sconnection.upgrade();

        // Confirm connected
        future.get(30,TimeUnit.SECONDS);
        tsocket.waitForConnected(30,TimeUnit.SECONDS);

        int messageCount = 10;

        // Setup server read thread
        ServerReadThread reader = new ServerReadThread(sconnection, messageCount);
        reader.start();

        // Have client write slowly.
        ClientWriteThread writer = new ClientWriteThread(tsocket.getSession());
        writer.setMessageCount(messageCount);
        writer.setMessage("Hello");
        writer.setSlowness(10);
        writer.start();
        writer.join();

        reader.waitForExpectedMessageCount(1, TimeUnit.MINUTES);

        // Verify receive
        Assert.assertThat("Frame Receive Count", reader.getFrameCount(), is(messageCount));

        // Close
        tsocket.getSession().close(StatusCode.NORMAL, "Done");

        Assert.assertTrue("Client Socket Closed", tsocket.closeLatch.await(3, TimeUnit.MINUTES));
        tsocket.assertCloseCode(StatusCode.NORMAL);

        reader.cancel(); // stop reading
    }
