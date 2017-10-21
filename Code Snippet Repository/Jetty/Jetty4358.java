    @Test
    public void testPUTRequestWithPreflight() throws Exception
    {
        FilterHolder filterHolder = new FilterHolder(new CrossOriginFilter());
        filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "PUT");
        tester.getContext().addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

        CountDownLatch latch = new CountDownLatch(1);
        tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");

        // Preflight request
        String request = "" +
                "OPTIONS / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                CrossOriginFilter.ACCESS_CONTROL_REQUEST_METHOD_HEADER + ": PUT\r\n" +
                "Origin: http://localhost\r\n" +
                "\r\n";
        String response = tester.getResponses(request);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_MAX_AGE_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_METHODS_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_HEADERS_HEADER));
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));

        // Preflight request was ok, now make the actual request
        request = "" +
                "PUT / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "Origin: http://localhost\r\n" +
                "\r\n";
        response = tester.getResponses(request);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER));
    }
