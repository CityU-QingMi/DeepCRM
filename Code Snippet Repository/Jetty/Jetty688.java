    @Test(timeout=60000)
    public void testRequestResponse() throws Exception
    {
        final SSLSocket client = newClient();

        SimpleProxy.AutomaticFlow automaticProxyFlow = proxy.startAutomaticFlow();
        client.startHandshake();
        Assert.assertTrue(automaticProxyFlow.stop(5, TimeUnit.SECONDS));

        Future<Object> request = threadPool.submit(() ->
        {
            OutputStream clientOutput = client.getOutputStream();
            clientOutput.write(("" +
                    "GET / HTTP/1.1\r\n" +
                    "Host: localhost\r\n" +
                    "\r\n").getBytes(StandardCharsets.UTF_8));
            clientOutput.flush();
            return null;
        });

        // Application data
        TLSRecord record = proxy.readFromClient();
        proxy.flushToServer(record);
        Assert.assertNull(request.get(5, TimeUnit.SECONDS));

        // Application data
        record = proxy.readFromServer();
        Assert.assertEquals(TLSRecord.Type.APPLICATION, record.getType());
        proxy.flushToClient(record);

        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
        String line = reader.readLine();
        Assert.assertNotNull(line);
        Assert.assertTrue(line.startsWith("HTTP/1.1 200 "));
        while ((line = reader.readLine()) != null)
        {
            if (line.trim().length() == 0)
                break;
        }

        // Check that we did not spin
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertThat(sslFills.get(), Matchers.lessThan(20));
        Assert.assertThat(sslFlushes.get(), Matchers.lessThan(20));
        Assert.assertThat(httpParses.get(), Matchers.lessThan(20));

        closeClient(client);
    }
