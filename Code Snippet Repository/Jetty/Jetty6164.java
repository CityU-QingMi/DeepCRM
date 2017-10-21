    @Test
    public void testEndpointHandshakeInfo() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        EndpointEchoClient echoer = new EndpointEchoClient();

        // Build Config
        ClientEndpointConfig.Builder cfgbldr = ClientEndpointConfig.Builder.create();
        TrackingConfigurator configurator = new TrackingConfigurator();
        cfgbldr.configurator(configurator);
        ClientEndpointConfig config = cfgbldr.build();

        // Connect
        Session session = container.connectToServer(echoer,config,serverUri);

        // Send Simple Message
        session.getBasicRemote().sendText("Echo");

        // Wait for echo
        echoer.textCapture.messageQueue.awaitMessages(1,1000,TimeUnit.MILLISECONDS);

        // Validate client side configurator use
        Assert.assertThat("configurator.request",configurator.request,notNullValue());
        Assert.assertThat("configurator.response",configurator.response,notNullValue());
    }
