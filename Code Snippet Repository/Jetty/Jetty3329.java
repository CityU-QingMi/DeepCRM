    @Test
    public void testEmptyHost() throws Exception
    {
        String response;

        response=connector.getResponse("GET / HTTP/1.1\r\n"+
            "Host:\r\n"+
            "\r\n");
        checkContains(response,0,"HTTP/1.1 200");
    }
