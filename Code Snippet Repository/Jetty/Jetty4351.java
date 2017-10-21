    @Test
    public void testMultipleSessionTracking() throws Exception
    {
        // get some session ids, first
        String requestSession="GET /ctx/dos/test?session=true HTTP/1.1\r\nHost: localhost\r\n\r\n";
        String closeRequest="GET /ctx/dos/test?session=true HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String response=doRequests(requestSession+requestSession,1,0,0,closeRequest);

        String[] sessions = response.split("\r\n\r\n");

        String sessionId1=sessions[0].substring(sessions[0].indexOf("Set-Cookie: ")+12, sessions[0].indexOf(";"));
        String sessionId2=sessions[1].substring(sessions[1].indexOf("Set-Cookie: ")+12, sessions[1].indexOf(";"));

        // alternate between sessions
        String request1="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nCookie: " + sessionId1 + "\r\n\r\n";
        String request2="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nCookie: " + sessionId2 + "\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\nCookie: " + sessionId2 + "\r\n\r\n";

        // ensure the sessions are new
        doRequests(request1+request2,1,1100,1100,last);
        Thread.sleep(1000);

        String responses = doRequests(request1+request2+request1+request2+request1,2,1100,1100,last);

        Assert.assertEquals(11,count(responses,"HTTP/1.1 200 OK"));
        Assert.assertEquals(0,count(responses,"DoSFilter: delayed"));

        // alternate between sessions
        responses = doRequests(request1+request2+request1+request2+request1,2,250,250,last);

        // System.err.println(responses);
        Assert.assertEquals(11,count(responses,"HTTP/1.1 200 OK"));
        int delayedRequests = count(responses,"DoSFilter: delayed");
        Assert.assertTrue("delayedRequests: " + delayedRequests + " is not between 2 and 5",delayedRequests >= 2 && delayedRequests <= 5);
    }
