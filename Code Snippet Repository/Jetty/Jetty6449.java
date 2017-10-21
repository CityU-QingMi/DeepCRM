    @Test
    public void testConnectionRefused() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        // Intentionally bad port with nothing listening on it
        URI wsUri = new URI("ws://127.0.0.1:1");

        try
        {
            Future<Session> future = client.connect(wsocket,wsUri);

            // The attempt to get upgrade response future should throw error
            future.get(3,TimeUnit.SECONDS);
            Assert.fail("Expected ExecutionException -> ConnectException");
        }
        catch (ConnectException e)
        {
            Throwable t = wsocket.errorQueue.remove();
            Assert.assertThat("Error Queue[0]",t,instanceOf(ConnectException.class));
            wsocket.assertNotOpened();
        }
        catch (ExecutionException e)
        {
            assertExpectedError(e, wsocket,
                    anyOf(
                            instanceOf(UpgradeException.class),
                            instanceOf(SocketTimeoutException.class),
                            instanceOf(ConnectException.class)));
        }
    }
