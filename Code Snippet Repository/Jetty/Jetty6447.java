    @Test
    public void testBadUpgrade() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        IBlockheadServerConnection connection = server.accept();
        connection.readRequest();
        // Upgrade badly
        connection.respond("HTTP/1.1 101 Upgrade\r\n" + "Sec-WebSocket-Accept: rubbish\r\n" + "\r\n");

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
