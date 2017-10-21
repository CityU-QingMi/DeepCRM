    @Test
    public void testBadURIencoding() throws Exception
    {
        Log.getLogger(HttpParser.class).info("badMessage: bad encoding expected ...");
        String response;

        try(StacklessLogging stackless = new StacklessLogging(HttpParser.class))
        {
            response=connector.getResponse("GET /bad/encoding%1 HTTP/1.1\r\n"+
                    "Host: localhost\r\n"+
                    "Connection: close\r\n"+
                    "\r\n");
            checkContains(response,0,"HTTP/1.1 400");
        }
    }
