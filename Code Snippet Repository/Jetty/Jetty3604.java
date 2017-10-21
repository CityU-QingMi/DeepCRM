    @Test
    public void testBadPort() throws Exception
    {
        try(StacklessLogging stackless = new StacklessLogging(ProxyConnectionFactory.class))
        {
            String response=_connector.getResponse("PROXY TCP 1.2.3.4 5.6.7.8 9999999999999 222\r\n"+
                    "GET /path HTTP/1.1\n"+
                    "Host: server:80\n"+
                    "Connection: close\n"+
                    "\n");
        Assert.assertNull(response);
        }
    }
