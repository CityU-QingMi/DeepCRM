    @Test
    public void testOpenStartRange() throws Exception
    {
        String response;
        response = connector.getResponse(
                "GET /context/data.txt HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n"+
                "Range: bytes=-20\r\n" +
                "\r\n");
        assertResponseContains("206 Partial", response);
        assertResponseNotContains("Content-Type: multipart/byteranges; boundary=", response);
        assertResponseContains("Content-Range: bytes 60-79/80", response); // yes the spec says it is these bytes
        assertResponseContains(DATA.substring(60), response);
    }
