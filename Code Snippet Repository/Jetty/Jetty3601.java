    @Test
    public void testTooLong() throws Exception
    {
        String response=_connector.getResponse("PROXY TOOLONG!!! eeee:eeee:eeee:eeee:0000:0000:0000:0000 ffff:ffff:ffff:ffff:0000:0000:0000:0000 65535 65535\r\n"+
                "GET /path HTTP/1.1\n"+
                "Host: server:80\n"+
                "Connection: close\n"+
                "\n");
        
        Assert.assertNull(response);
    }
