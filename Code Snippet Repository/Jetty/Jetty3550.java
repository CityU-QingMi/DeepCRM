    @Test
    public void testThreeResponse_11() throws Exception
    {
        LocalEndPoint endp = _connector.connect();
        endp.addInput(
            "GET /R1 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n");

        String response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));
        
        endp.addInput(
            "GET /R2 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n");

        response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R2"));

        endp.addInput(
            "GET /R3 HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "\r\n"
            );
        
        response=endp.getResponse();
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R3"));
    }
