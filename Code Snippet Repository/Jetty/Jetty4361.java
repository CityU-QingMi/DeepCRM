    @Test
    public void testCrossOriginFilterDisabledForWebSocketUpgrade() throws Exception
    {
        FilterHolder filterHolder = new FilterHolder(new CrossOriginFilter());
        tester.getContext().addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

        CountDownLatch latch = new CountDownLatch(1);
        tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");

        String request = "" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: Upgrade\r\n" +
                "Upgrade: WebSocket\r\n" +
                "Origin: http://localhost\r\n" +
                "\r\n";
        String response = tester.getResponses(request,1,TimeUnit.SECONDS);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertFalse(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER));
        Assert.assertFalse(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER));
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
