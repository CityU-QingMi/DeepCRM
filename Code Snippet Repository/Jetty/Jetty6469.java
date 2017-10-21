    @Test
    @Slow
    public void testServerSlowToRead() throws Exception
    {
        JettyTrackingSocket tsocket = new JettyTrackingSocket();
        client.setMasker(new ZeroMasker());
        client.setMaxIdleTimeout(60000);

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(tsocket,wsUri);

        IBlockheadServerConnection sconnection = server.accept();
        sconnection.setSoTimeout(60000);
        sconnection.upgrade();

        // Confirm connected
        future.get(30,TimeUnit.SECONDS);
        tsocket.waitForConnected(30,TimeUnit.SECONDS);

        int messageCount = 10;

        // Setup slow server read thread
        ServerReadThread reader = new ServerReadThread(sconnection, messageCount);
        reader.setSlowness(100); // slow it down
        reader.start();

        // Have client write as quickly as it can.
        ClientWriteThread writer = new ClientWriteThread(tsocket.getSession());
        writer.setMessageCount(messageCount);
        writer.setMessage("Hello");
        writer.setSlowness(-1); // disable slowness
        writer.start();
        writer.join();

        // Verify receive
        reader.waitForExpectedMessageCount(10,TimeUnit.SECONDS);
        Assert.assertThat("Frame Receive Count",reader.getFrameCount(),is(messageCount));

        // Close
        tsocket.getSession().close(StatusCode.NORMAL,"Done");

        Assert.assertTrue("Client Socket Closed",tsocket.closeLatch.await(10,TimeUnit.SECONDS));
        tsocket.assertCloseCode(StatusCode.NORMAL);

        reader.cancel(); // stop reading
    }
