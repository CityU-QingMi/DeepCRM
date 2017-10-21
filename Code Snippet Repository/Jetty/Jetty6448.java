    @Test
    public void testConnectionNotAccepted() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        URI wsUri = server.getWsUri();
        Future<Session> future = client.connect(wsocket,wsUri);

        // Intentionally not accept incoming socket.
        // server.accept();

        try
        {
            future.get(3,TimeUnit.SECONDS);
            Assert.fail("Should have Timed Out");
        }
        catch (ExecutionException e)
        {
            assertExpectedError(e,wsocket,instanceOf(UpgradeException.class));
            // Possible Passing Path (active session wait timeout)
            wsocket.assertNotOpened();
        }
        catch (TimeoutException e)
        {
            // Possible Passing Path (concurrency timeout)
            wsocket.assertNotOpened();
        }
    }
