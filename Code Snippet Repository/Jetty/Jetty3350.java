    @Test
    public void testEmptyNotPersistent() throws Exception
    {
        String response=connector.getResponse("GET /R1?empty=true HTTP/1.0\r\n"+
            "Host: localhost\r\n"+
            "\r\n");

        int offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkNotContained(response,offset,"Content-Length");

        response=connector.getResponse("GET /R1?empty=true HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");

        offset=0;
        offset = checkContains(response,offset,"HTTP/1.1 200");
        checkContains(response,offset,"Connection: close");
        checkNotContained(response,offset,"Content-Length");
    }
