    @Test
    public void testInit_HttpClient_SyntheticStart() throws Exception
    {
        HttpClient http = null;
        WebSocketClient ws = new WebSocketClient();
        ws.start();
        try
        {
            http = ws.getHttpClient();

            assertThat("WebSocketClient started",ws.isStarted(),is(true));
            assertThat("HttpClient started",http.isStarted(),is(true));

            HttpClient httpBean = ws.getBean(HttpClient.class); 
            assertThat("HttpClient bean found in WebSocketClient",httpBean,is(http));
            assertThat("HttpClient bean is managed",ws.isManaged(httpBean),is(true));
            assertThat("WebSocketClient should not be found in HttpClient",http.getBean(WebSocketClient.class),nullValue());
        }
        finally
        {
            ws.stop();
        }

        assertThat("WebSocketClient stopped",ws.isStopped(),is(true));
        assertThat("HttpClient stopped",http.isStopped(),is(true));
    }
