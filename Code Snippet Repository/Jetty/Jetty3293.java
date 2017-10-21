    @Test
    public void test404EmptyAccept() throws Exception
    {
        String response = connector.getResponse(
            "GET / HTTP/1.1\r\n"+
            "Accept: \r\n"+
            "Host: Localhost\r\n"+
            "\r\n");
        assertThat(response,startsWith("HTTP/1.1 404 "));
        assertThat(response,containsString("Content-Length: 0"));
        assertThat(response,not(containsString("Content-Type")));
    }
