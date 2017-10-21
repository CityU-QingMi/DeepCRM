    @Test
    public void testBurstLowRateIP() throws Exception
    {
        String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String responses = doRequests(request+request+request+request,2,1100,1100,last);

        Assert.assertEquals(9,count(responses,"HTTP/1.1 200 OK"));
        Assert.assertEquals(0,count(responses,"DoSFilter:"));
    }
