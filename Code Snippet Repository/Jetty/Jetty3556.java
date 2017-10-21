    @Test
    public void testTwoGETsParsed() throws Exception
    {
        LocalConnector.LocalEndPoint endp = _connector.executeRequest(
            "GET /R1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R2 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n");

        String response = BufferUtil.toString(endp.waitForResponse(false,10,TimeUnit.SECONDS),StandardCharsets.ISO_8859_1);
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));
        
        response = BufferUtil.toString(endp.waitForResponse(false,10,TimeUnit.SECONDS),StandardCharsets.ISO_8859_1);
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R2"));
    }
