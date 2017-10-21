    @Test
    public void testConnection() throws Exception
    {
        String response=null;
        try
        {
            int offset=0;
            response=connector.getResponse("GET /R1 HTTP/1.1\r\n"+
                                           "Host: localhost\r\n"+
                                           "Connection: TE, close\r\n"+
                                           "Transfer-Encoding: chunked\r\n"+
                                           "Content-Type: text/plain; charset=utf-8\r\n"+
                                           "\r\n"+
                                           "5;\r\n"+
                                           "12345\r\n"+
                                           "0;\r\n" +
                                           "\r\n");
            checkContains(response,offset,"Connection: close");
        }
        catch (Exception e)
        {
            if(response != null)
                System.err.println(response);
            throw e;
        }
    }
