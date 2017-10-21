    @Test
    public void testServerSessionRequestURI() throws Exception
    {
        Assert.assertThat("server scheme",server.getServerUri().getScheme(),is("wss"));
        WebSocketClient client = new WebSocketClient(server.getSslContextFactory(),null,bufferPool);
        try
        {
            client.setConnectTimeout(CONNECT_TIMEOUT);
            client.start();

            CaptureSocket clientSocket = new CaptureSocket();
            URI requestUri = server.getServerUri().resolve("/deep?a=b");
            System.err.printf("Request URI: %s%n",requestUri.toASCIIString());
            Future<Session> fut = client.connect(clientSocket,requestUri);

            // wait for connect
            Session session = fut.get(FUTURE_TIMEOUT_SEC,TimeUnit.SECONDS);

            // Generate text frame
            RemoteEndpoint remote = session.getRemote();
            remote.sendString("session.upgradeRequest.requestURI");
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();

            // Read frame (hopefully text frame)
            clientSocket.messages.awaitEventCount(1,30,TimeUnit.SECONDS);
            EventQueue<String> captured = clientSocket.messages;
            String expected = String.format("session.upgradeRequest.requestURI=%s",requestUri.toASCIIString());
            Assert.assertThat("session.upgradeRequest.requestURI",captured.poll(),is(expected));

            // Shutdown the socket
            clientSocket.close();
        }
        finally
        {
            client.stop();
        }
    }
