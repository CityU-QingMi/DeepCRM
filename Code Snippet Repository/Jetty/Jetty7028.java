    @Test
    public void testServerSessionIsSecure() throws Exception
    {
        Assert.assertThat("server scheme",server.getServerUri().getScheme(),is("wss"));
        WebSocketClient client = new WebSocketClient(server.getSslContextFactory(),null,bufferPool);
        try
        {
            client.setConnectTimeout(CONNECT_TIMEOUT);
            client.start();

            CaptureSocket clientSocket = new CaptureSocket();
            URI requestUri = server.getServerUri();
            System.err.printf("Request URI: %s%n",requestUri.toASCIIString());
            Future<Session> fut = client.connect(clientSocket,requestUri);

            // wait for connect
            Session session = fut.get(FUTURE_TIMEOUT_SEC,TimeUnit.SECONDS);

            // Generate text frame
            RemoteEndpoint remote = session.getRemote();
            remote.sendString("session.isSecure");
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();

            // Read frame (hopefully text frame)
            clientSocket.messages.awaitEventCount(1,30,TimeUnit.SECONDS);
            EventQueue<String> captured = clientSocket.messages;
            Assert.assertThat("Server.session.isSecure",captured.poll(),is("session.isSecure=true"));

            // Shutdown the socket
            clientSocket.close();
        }
        finally
        {
            client.stop();
        }
    }
