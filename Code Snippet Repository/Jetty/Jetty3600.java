    @Test
    public void testIPv6() throws Exception
    {
        String response=_connector.getResponse("PROXY UNKNOWN eeee:eeee:eeee:eeee:eeee:eeee:eeee:eeee ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff 65535 65535\r\n"+
                "GET /path HTTP/1.1\n"+
                "Host: server:80\n"+
                "Connection: close\n"+
                "\n");
        
        Assert.assertThat(response,Matchers.containsString("HTTP/1.1 200"));
        Assert.assertThat(response,Matchers.containsString("pathInfo=/path"));
        Assert.assertThat(response,Matchers.containsString("remote=eeee:eeee:eeee:eeee:eeee:eeee:eeee:eeee:65535"));
        Assert.assertThat(response,Matchers.containsString("local=ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff:65535"));
    }
