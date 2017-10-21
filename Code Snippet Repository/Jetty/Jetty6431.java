    @Test
    public void testAbruptClientClose() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection ssocket = server.accept();
        ssocket.upgrade();

        // Validate that we are connected
        future.get(30,TimeUnit.SECONDS);
        wsocket.waitForConnected(30,TimeUnit.SECONDS);

        // Have client disconnect abruptly
        Session session = wsocket.getSession();
        session.disconnect();

        // Client Socket should see close
        wsocket.waitForClose(10,TimeUnit.SECONDS);

        // Client Socket should see a close event, with status NO_CLOSE
        // This event is automatically supplied by the underlying WebSocketClientConnection
        // in the situation of a bad network connection.
        wsocket.assertCloseCode(StatusCode.NO_CLOSE);
    }
