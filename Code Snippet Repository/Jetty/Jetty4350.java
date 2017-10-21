    @Test
    public void testSessionTracking() throws Exception
    {
        // get a session, first
        String requestSession="GET /ctx/dos/test?session=true HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String response=doRequests("",1,0,0,requestSession);
        String sessionId=response.substring(response.indexOf("Set-Cookie: ")+12, response.indexOf(";"));

        // all other requests use this session
        String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nCookie: " + sessionId + "\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\nCookie: " + sessionId + "\r\n\r\n";
        String responses = doRequests(request+request+request+request+request,2,1100,1100,last);

        Assert.assertEquals(11,count(responses,"HTTP/1.1 200 OK"));
        Assert.assertEquals(2,count(responses,"DoSFilter: delayed"));
    }
