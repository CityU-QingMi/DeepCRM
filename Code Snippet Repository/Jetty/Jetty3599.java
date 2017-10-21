    @Test
    public void testSimple() throws Exception
    {
        String response=_connector.getResponse("PROXY TCP 1.2.3.4 5.6.7.8 111 222\r\n"+
                "GET /path HTTP/1.1\n"+
                "Host: server:80\n"+
                "Connection: close\n"+
                "\n");
        
        Assert.assertThat(response,Matchers.containsString("HTTP/1.1 200"));
        Assert.assertThat(response,Matchers.containsString("pathInfo=/path"));
        Assert.assertThat(response,Matchers.containsString("local=5.6.7.8:222"));
        Assert.assertThat(response,Matchers.containsString("remote=1.2.3.4:111"));
    }
