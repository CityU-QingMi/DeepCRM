    @Test
    public void testGETandGET() throws Exception
    {
        String response=_connector.getResponse("GET /R1 HTTP/1.0\r\n\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R1"));

        response=_connector.getResponse("GET /R2 HTTP/1.0\r\n\r\n");
        assertThat(response,containsString("HTTP/1.1 200 OK"));
        assertThat(response,containsString("pathInfo=/R2"));
    }
