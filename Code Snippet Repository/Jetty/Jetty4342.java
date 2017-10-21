    @Test
    public void testUnresponsiveClient() throws Exception
    {
        int numRequests = 1000;

        String last="GET /ctx/timeout/unresponsive?lines="+numRequests+" HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String responses = doRequests("",0,0,0,last);
        // was expired, and stopped before reaching the end of the requests
        int responseLines = count(responses, "Line:");
        Assert.assertThat(responseLines,Matchers.greaterThan(0));
        Assert.assertThat(responseLines,Matchers.lessThan(numRequests));
    }
