    @Test
    public void testNoRangeRequests() throws Exception
    {
        String response;

        response= connector.getResponse(
                "GET /context/data.txt HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "Connection: close\r\n"+
                        "\r\n");
        assertResponseContains("200 OK", response);
        assertResponseContains("Accept-Ranges: bytes", response);
        assertResponseContains(DATA,response);
    }
