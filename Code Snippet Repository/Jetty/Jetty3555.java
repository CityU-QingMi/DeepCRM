    @Test
    public void testTwoGETs() throws Exception
    {
        LocalEndPoint endp = _connector.connect();
        endp.addInput(
            "GET /R1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R2 HTTP/1.0\r\n\r\n");

        String response = endp.getResponse() + endp.getResponse();
        
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));

        response=response.substring(response.indexOf("</html>")+8);

        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R2"));
    }
