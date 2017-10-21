    @Test
    public void testBadHostPort() throws Exception
    {
        Log.getLogger(HttpParser.class).info("badMessage: Number formate exception expected ...");
        String response;

        response=connector.getResponse("GET http://localhost:EXPECTED_NUMBER_FORMAT_EXCEPTION/ HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");
        checkContains(response,0,"HTTP/1.1 400");
    }
