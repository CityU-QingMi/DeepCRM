    @Test
    public void testFragmentedChunk() throws Exception
    {
        String response=null;
        try
        {
            int offset=0;

            // Chunk last
            response=connector.getResponse("GET /R1 HTTP/1.1\r\n"+
                                           "Host: localhost\r\n"+
                                           "Transfer-Encoding: chunked\r\n"+
                                           "Content-Type: text/plain\r\n"+
                                           "Connection: close\r\n"+
                                           "\r\n"+
                                           "5;\r\n"+
                                           "12345\r\n"+
                                           "0;\r\n" +
                                           "\r\n");
            offset = checkContains(response,offset,"HTTP/1.1 200");
            offset = checkContains(response,offset,"/R1");
            checkContains(response,offset,"12345");

            offset = 0;
            response=connector.getResponse("GET /R2 HTTP/1.1\r\n"+
                                           "Host: localhost\r\n"+
                                           "Transfer-Encoding: chunked\r\n"+
                                           "Content-Type: text/plain\r\n"+
                                           "Connection: close\r\n"+
                                           "\r\n"+
                                           "5;\r\n"+
                                           "ABCDE\r\n"+
                                           "0;\r\n" +
                                           "\r\n");
            offset = checkContains(response,offset,"HTTP/1.1 200");
            offset = checkContains(response,offset,"/R2");
            checkContains(response,offset,"ABCDE");
        }
        catch(Exception e)
        {
            if(response != null)
                System.err.println(response);
            throw e;
        }
    }
