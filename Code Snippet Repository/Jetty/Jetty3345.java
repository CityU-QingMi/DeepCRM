    @Test
    public void testCONNECT() throws Exception
    {
        String response = null;
        try
        {
            int offset=0;

            response=connector.getResponse("CONNECT www.webtide.com:8080 HTTP/1.1\r\n"+
                                           "Host: myproxy:8888\r\n"+
                                           "\r\n",200,TimeUnit.MILLISECONDS);
            checkContains(response,offset,"HTTP/1.1 200");
        }
        catch (Exception e)
        {
            if(response != null)
                System.err.println(response);
            throw e;
        }
    }
