    private void assertConnectionTimeout(URI uri) throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            JettyEchoSocket clientEcho = new JettyEchoSocket();
            if (LOG.isDebugEnabled())
                LOG.debug("Client Attempting to connect");
            Future<Session> future = client.connect(clientEcho,uri);
            // wait for connect
            future.get(1,TimeUnit.SECONDS);
            if (LOG.isDebugEnabled())
                LOG.debug("Client Connected");
            // wait 1 second
            if (LOG.isDebugEnabled())
                LOG.debug("Waiting 1 second");
            TimeUnit.SECONDS.sleep(1);
            if (LOG.isDebugEnabled())
                LOG.debug("Waited 1 second");
            if (clientEcho.getClosed() == false)
            {
                // Try to write
                clientEcho.sendMessage("You shouldn't be there");
                try
                {
                    Queue<String> msgs = clientEcho.awaitMessages(1);
                    assertThat("Should not have received messages echoed back",msgs,is(empty()));
                }
                catch (TimeoutException | InterruptedException e)
                {
                    // valid success path
                }
            }
        }
        finally
        {
            client.stop();
        }
    }
