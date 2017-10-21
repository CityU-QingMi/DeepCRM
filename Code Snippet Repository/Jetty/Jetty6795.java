    @Test
    public void testAdapterConnectCloseSocket()
    {
        EventDriverFactory factory = new EventDriverFactory(new SimpleContainerScope(WebSocketPolicy.newClientPolicy()));
        AdapterConnectCloseSocket socket = new AdapterConnectCloseSocket();
        EventDriver driver = factory.wrap(socket);

        String classId = AdapterConnectCloseSocket.class.getSimpleName();
        Assert.assertThat("EventDriver for " + classId,driver,instanceOf(JettyListenerEventDriver.class));
    }
