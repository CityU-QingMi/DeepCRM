    @Test
    public void testBadUTF8FallsbackTo8859() throws Exception
    {
        Log.getLogger(HttpParser.class).info("badMessage: bad encoding expected ...");
        String response;

        response=connector.getResponse("GET /foo/bar%c0%00 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");
        checkContains(response,0,"HTTP/1.1 200"); //now fallback to iso-8859-1

        response=connector.getResponse("GET /bad/utf8%c1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");
        checkContains(response,0,"HTTP/1.1 200"); //now fallback to iso-8859-1
    }
