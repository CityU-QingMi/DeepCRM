    @Test
    public void testOneResponse_11_chunked() throws Exception
    {
        String response=_connector.getResponse(
            "GET /R1?flush=true HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));
        assertThat(response,containsString("\r\n0\r\n"));
    }
