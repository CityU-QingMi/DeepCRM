    @Test
    public void test404PreferJson() throws Exception
    {
        String response = connector.getResponse(
            "GET / HTTP/1.1\r\n"+
            "Host: Localhost\r\n"+
            "Accept: text/html;q=0.5,text/json;q=1.0,*/*\r\n"+
            "Accept-Charset: *\r\n"+
            "\r\n");
        
        assertThat(response,startsWith("HTTP/1.1 404 "));
        assertThat(response,not(containsString("Content-Length: 0")));
        assertThat(response,containsString("Content-Type: text/json"));
    }
