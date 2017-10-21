    @Test
    public void testBadHandshake_SwitchingProtocols_NoConnectionHeader() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection connection = server.accept();
        List<String> requestLines = connection.readRequestLines();
        String key = connection.parseWebSocketKey(requestLines);

        // Send Switching Protocols 101, but no 'Connection' header
        StringBuilder resp = new StringBuilder();
        resp.append("HTTP/1.1 101 Switching Protocols\r\n");
        resp.append("Sec-WebSocket-Accept: ").append(AcceptHash.hashKey(key)).append("\r\n");
        // Intentionally leave out Connection header
        resp.append("\r\n");
        connection.respond(resp.toString());

        // The attempt to get upgrade response future should throw error
        try
        {
            future.get(30,TimeUnit.SECONDS);
            Assert.fail("Expected ExecutionException -> UpgradeException");
        }
        catch (ExecutionException e)
        {
            // Expected Path
            UpgradeException ue = assertExpectedError(e,wsocket,instanceOf(UpgradeException.class));
            Assert.assertThat("UpgradeException.requestURI",ue.getRequestURI(),notNullValue());
            Assert.assertThat("UpgradeException.requestURI",ue.getRequestURI().toASCIIString(),is(wsUri.toASCIIString()));
            Assert.assertThat("UpgradeException.responseStatusCode",ue.getResponseStatusCode(),is(101));
        }
    }
