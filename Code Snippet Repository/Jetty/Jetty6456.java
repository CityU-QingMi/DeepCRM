    @Test
    public void testBadHandshake_GetOK_WithSecWebSocketAccept() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection connection = server.accept();
        List<String> requestLines = connection.readRequestLines();
        String key = connection.parseWebSocketKey(requestLines);

        // Send OK to GET but not upgrade
        StringBuilder resp = new StringBuilder();
        resp.append("HTTP/1.1 200 OK\r\n"); // intentionally 200 (not 101)
        // Include a value accept key
        resp.append("Sec-WebSocket-Accept: ").append(AcceptHash.hashKey(key)).append("\r\n");
        resp.append("Content-Length: 0\r\n");
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
            Assert.assertThat("UpgradeException.responseStatusCode",ue.getResponseStatusCode(),is(200));
        }
    }
