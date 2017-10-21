    @Test
    public void testExpectContinues() throws Exception
    {
        LocalEndPoint endp = _connector.executeRequest(
            "GET /R1 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "Content-Type: text/plain; charset=UTF-8\r\n" +
            "Expect: 100-Continue\r\n" +
            "Content-Length: 10\r\n" +
            "\r\n");
        String response = endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 100 Continue"));
        endp.addInput("01234567890\r\n");
        response = endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));       
        assertThat(response,containsString("pathInfo=/R1"));     
        assertThat(response,containsString("0123456789"));
    }
