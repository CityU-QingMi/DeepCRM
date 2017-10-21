    @Test
    public void testInit_HttpClient_StartedOutside() throws Exception
    {
        HttpClient http = new HttpClient();
        http.start();

        try
        {
            WebSocketClient ws = new WebSocketClient(http);
            ws.start();
            try
            {
                assertThat("HttpClient",ws.getHttpClient(),is(http));

                assertThat("WebSocketClient started",ws.isStarted(),is(true));
                assertThat("HttpClient started",http.isStarted(),is(true));

                HttpClient httpBean = ws.getBean(HttpClient.class); 
                assertThat("HttpClient should not be found in WebSocketClient",httpBean,nullValue());
                assertThat("HttpClient bean is managed",ws.isManaged(httpBean),is(false));
                assertThat("WebSocketClient should not be found in HttpClient",http.getBean(WebSocketClient.class),nullValue());
            }
            finally
            {
                ws.stop();
            }
            assertThat("WebSocketClient stopped",ws.isStopped(),is(true));
            assertThat("HttpClient stopped",http.isStopped(),is(false));
        }
        finally
        {
            http.stop();
        }

        assertThat("HttpClient stopped",http.isStopped(),is(true));
    }
