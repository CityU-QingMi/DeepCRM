    @Test
    public void testEchoReturn() throws Exception
    {
        WSServer wsb = new WSServer(testdir,"app");
        wsb.copyWebInf("empty-web.xml");
        wsb.copyClass(EchoReturnEndpoint.class);

        try
        {
            wsb.start();
            URI uri = wsb.getServerBaseURI();

            WebAppContext webapp = wsb.createWebAppContext();
            wsb.deployWebapp(webapp);

            WebSocketClient client = new WebSocketClient(bufferPool);
            try
            {
                client.start();
                JettyEchoSocket clientEcho = new JettyEchoSocket();
                Future<Session> future = client.connect(clientEcho,uri.resolve("echoreturn"));
                // wait for connect
                future.get(1,TimeUnit.SECONDS);
                clientEcho.sendMessage("Hello World");
                Queue<String> msgs = clientEcho.awaitMessages(1);
                Assert.assertEquals("Expected message","Hello World",msgs.poll());
            }
            finally
            {
                client.stop();
            }
        }
        finally
        {
            wsb.stop();
        }
    }
