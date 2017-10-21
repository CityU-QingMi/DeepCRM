    @Test
    public void testListenerBasicSocket()
    {
        EventDriverFactory factory = new EventDriverFactory(new SimpleContainerScope(WebSocketPolicy.newClientPolicy()));
        ListenerBasicSocket socket = new ListenerBasicSocket();
        EventDriver driver = factory.wrap(socket);

        String classId = ListenerBasicSocket.class.getSimpleName();
        Assert.assertThat("EventDriver for " + classId,driver,instanceOf(JettyListenerEventDriver.class));
    }
