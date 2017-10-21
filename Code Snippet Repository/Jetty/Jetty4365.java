    @Test
    public void testSimpleRequestWithWildcardOrigin() throws Exception
    {
        FilterHolder filterHolder = new FilterHolder(new CrossOriginFilter());
        tester.getContext().addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

        CountDownLatch latch = new CountDownLatch(1);
        tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");
        String origin = "http://foo.example.com";
        
        String request = "" +
        "GET / HTTP/1.1\r\n" +
        "Host: localhost\r\n" +
        "Connection: close\r\n" +
        "Origin: "+origin+"\r\n" +
        "\r\n";
        String response = tester.getResponses(request);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER));
        Assert.assertTrue(!response.contains("Vary"));
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
