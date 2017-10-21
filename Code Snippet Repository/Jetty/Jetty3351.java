    @Test
    public void testEmptyPersistent() throws Exception
    {
        String response=connector.getResponse("GET /R1?empty=true HTTP/1.0\r\n"+
            "Host: localhost\r\n"+
            "Connection: keep-alive\r\n"+
            "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkContains(response,offset,"Content-Length: 0");
        checkNotContained(response,offset,"Connection: close");

        response=connector.getResponse("GET /R1?empty=true HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n");

        offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkContains(response,offset,"Content-Length: 0");
        checkNotContained(response,offset,"Connection: close");
    }
