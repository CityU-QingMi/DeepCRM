    @Test
    public void testResponseWebSocketHeaderFormat() throws Exception
    {
        HttpTester.Request request = new HttpTester.Request();
        request.setMethod("GET");
        request.setURI("/ws/");
        request.setVersion(HttpVersion.HTTP_1_1);
        request.setHeader("Host", "test");

        ByteBuffer responseBuffer = connector.getResponse(request.generate());
        HttpTester.Response response = HttpTester.parseResponse(responseBuffer);

        // Now test for properly formatted HTTP Response Headers.
        assertThat("Response Code",response.getStatus(),is(101));
        assertThat("Response Header Upgrade",response.get("Upgrade"),is("WebSocket"));
        assertThat("Response Header Connection",response.get("Connection"),is("Upgrade"));
    }
