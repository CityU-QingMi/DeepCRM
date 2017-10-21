    @Test
    public void testThrottledIP() throws Exception
    {
        Thread other = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    // Cause a delay, then sleep while holding pass
                    String request="GET /ctx/dos/sleeper HTTP/1.1\r\nHost: localhost\r\n\r\n";
                    String last="GET /ctx/dos/sleeper?sleep=2000 HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
                    doRequests(request+request+request+request,1,0,0,last);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        other.start();
        Thread.sleep(1500);

        String request="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        String last="GET /ctx/dos/test HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n";
        String responses = doRequests(request+request+request+request,1,0,0,last);
        // System.out.println("responses are " + responses);
        Assert.assertEquals("200 OK responses", 5,count(responses,"HTTP/1.1 200 OK"));
        Assert.assertEquals("delayed responses", 1,count(responses,"DoSFilter: delayed"));
        Assert.assertEquals("throttled responses", 1,count(responses,"DoSFilter: throttled"));
        Assert.assertEquals("unavailable responses", 0,count(responses,"DoSFilter: unavailable"));

        other.join();
    }
