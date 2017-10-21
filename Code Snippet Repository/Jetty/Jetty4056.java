    @Test
    public void testMultipleRangeRequests() throws Exception
    {
        String response;
        response = connector.getResponse(
                "GET /context/data.txt HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n"+
                "Range: bytes=0-9,20-29,40-49\r\n" +
                "\r\n");
        int start = response.indexOf("--jetty");
        String body = response.substring(start);
        String boundary = body.substring(0, body.indexOf("\r\n"));
        assertResponseContains("206 Partial", response);
        assertResponseContains("Content-Type: multipart/byteranges; boundary=", response);
        assertResponseContains("Content-Range: bytes 0-9/80", response);
        assertResponseContains("Content-Range: bytes 20-29/80", response);
        assertResponseContains("Content-Range: bytes 40-49/80", response);
        assertResponseContains(DATA.substring(0,10), response);
        assertResponseContains(DATA.substring(20,30), response);
        assertResponseContains(DATA.substring(40,50), response);
        assertTrue(body.endsWith(boundary + "--\r\n"));

    }
