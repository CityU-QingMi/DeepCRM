    @Test
    public void testDelayedIP() throws Exception
    {
        String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String responses = doRequests(request+request+request+request+request,2,1100,1100,last);

        Assert.assertEquals(2,count(responses,"DoSFilter: delayed"));
        Assert.assertEquals(11,count(responses,"HTTP/1.1 200 OK"));
    }
