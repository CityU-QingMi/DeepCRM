    @Test
    public void testOneResponse_10_keep_alive_empty() throws Exception
    {
        String response=_connector.getResponse(
            "GET /R1?empty=true HTTP/1.0\r\n" +
            "Connection: keep-alive\r\n" +
            "\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,not(containsString("pathInfo=/R1")));
    }
