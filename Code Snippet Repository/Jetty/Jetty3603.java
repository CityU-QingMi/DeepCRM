    @Test
    public void testBadCRLF() throws Exception
    {
        String response=_connector.getResponse("PROXY TCP 1.2.3.4 5.6.7.8 111 222\r \n"+
                "GET /path HTTP/1.1\n"+
                "Host: server:80\n"+
                "Connection: close\n"+
                "\n");
        Assert.assertNull(response);
    }
