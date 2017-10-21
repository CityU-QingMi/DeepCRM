    @Test
    public void testSimpleRequestWithExposedHeaders() throws Exception
    {
        FilterHolder filterHolder = new FilterHolder(new CrossOriginFilter());
        filterHolder.setInitParameter("exposedHeaders", "Content-Length");
        tester.getContext().addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

        CountDownLatch latch = new CountDownLatch(1);
        tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");

        String request = "" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "Origin: http://localhost\r\n" +
                "\r\n";
        String response = tester.getResponses(request);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_EXPOSE_HEADERS_HEADER));
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
