    @Test
    public void testOneResponse_11_close() throws Exception
    {
        String response=_connector.getResponse(
            "GET /R1 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "Connection: close\r\n" +
            "\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));
    }
