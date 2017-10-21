    @Test
    public void testExpectContinuesAvailable() throws Exception
    {
        LocalEndPoint endp = _connector.connect();
        endp.addInput(
            "GET /R1 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "Content-Type: text/plain; charset=UTF-8\r\n" +
            "Expect: 100-Continue\r\n" +
            "Content-Length: 10\r\n" +
            "\r\n"+
            "01234567890\r\n");
        String response = endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));       
        assertThat(response,containsString("pathInfo=/R1"));     
        assertThat(response,containsString("0123456789"));
    }
