    @Test
    public void testBadNotASocket()
    {
        EventDriverFactory factory = new EventDriverFactory(new SimpleContainerScope(WebSocketPolicy.newClientPolicy()));
        try
        {
            NotASocket bad = new NotASocket();
            // Should toss exception
            factory.wrap(bad);
        }
        catch (InvalidWebSocketException e)
        {
            // Validate that we have clear error message to the developer
            Assert.assertThat(e.getMessage(),allOf(containsString(WebSocketListener.class.getSimpleName()),containsString(WebSocket.class.getSimpleName())));
        }
    }
