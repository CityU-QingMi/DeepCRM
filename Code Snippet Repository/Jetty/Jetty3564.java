    @Test
    public void testOneResponse_11_empty() throws Exception
    {
        String response=_connector.getResponse(
            "GET /R1?empty=true HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "Connection: close\r\n" +
            "\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,not(containsString("pathInfo=/R1")));
    }
