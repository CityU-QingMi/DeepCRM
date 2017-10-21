    @Test
    public void testHTTP() throws Exception
    {
        String response=_connector.getResponse(
                "GET /path HTTP/1.1\n"+
                "Host: server:80\n"+
                "Connection: close\n"+
                "\n");
        Assert.assertNull(response);
    }
