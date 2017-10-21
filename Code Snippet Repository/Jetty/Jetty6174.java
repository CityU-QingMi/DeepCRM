    @Test
    public void testNoExtraHttpClientThreads()
    {
        container = ContainerProvider.getWebSocketContainer();
        assertThat("Container", container, notNullValue());
    
        List<String> threadNames = getThreadNames((ContainerLifeCycle)container);
        assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketContainer@"))));
        assertThat("Threads", threadNames, not(hasItem(containsString("HttpClient@"))));
    }
