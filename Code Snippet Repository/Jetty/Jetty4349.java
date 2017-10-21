    @Test
    public void testUnavailableIP() throws Exception
    {
        Thread other = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    // Cause a delay, then sleep while holding pass
                    String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\n\r\n";
                    String last="GET /ctx/dos/test?sleep=5000 HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
                    doRequests(request+request+request+request,1,0,0,last);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        other.start();
        Thread.sleep(500);

        String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String responses = doRequests(request+request+request+request,1,0,0,last);

        // System.err.println("RESPONSES: \n"+responses);

        Assert.assertEquals(4,count(responses,"HTTP/1.1 200 OK"));
        Assert.assertEquals(1,count(responses,"HTTP/1.1 429"));
        Assert.assertEquals(1,count(responses,"DoSFilter: delayed"));
        Assert.assertEquals(1,count(responses,"DoSFilter: throttled"));
        Assert.assertEquals(1,count(responses,"DoSFilter: unavailable"));

        other.join();
    }
