    @Test
    public void testPrefixRangeRequests() throws Exception
    {
        String response;

        response = connector.getResponse(
                "GET /context/data.txt HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "Connection: close\r\n"+
                        "Range: bytes=0-9\r\n" +
                        "\r\n");
        assertResponseContains("206 Partial", response);
        assertResponseContains("Content-Type: text/plain", response);
        assertResponseContains("Content-Range: bytes 0-9/80", response);
        assertResponseContains(DATA.substring(0,10), response);
    }
