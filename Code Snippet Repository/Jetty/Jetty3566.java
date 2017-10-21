    @Test
    public void testThreeResponsePipeline_11() throws Exception
    {
        LocalEndPoint endp = _connector.connect();
        endp.addInput(
            "GET /R1 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n"+
            "GET /R2 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n"+
            "GET /R3 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n"
            );
        String response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));
        response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R2"));
        response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R3"));
    }
