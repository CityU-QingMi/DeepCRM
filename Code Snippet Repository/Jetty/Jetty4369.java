    @Test
    public void testSimpleRequestWithMatchingOriginAndNonMatchingTimingOrigin() throws Exception
    {
    	FilterHolder filterHolder = new FilterHolder(new CrossOriginFilter());
    	String origin = "http://localhost";
    	String timingOrigin = "http://127.0.0.1";
    	filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, origin);
    	filterHolder.setInitParameter(CrossOriginFilter.ALLOWED_TIMING_ORIGINS_PARAM, timingOrigin);
    	tester.getContext().addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));
    	
    	CountDownLatch latch = new CountDownLatch(1);
    	tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");
    	
    	String request = "" +
    			"GET / HTTP/1.1\r\n" +
    			"Host: localhost\r\n" +
    			"Connection: close\r\n" +
    			"Origin: " + origin + "\r\n" +
    			"\r\n";
    	String response = tester.getResponses(request);
    	Assert.assertTrue(response.contains("HTTP/1.1 200"));
    	Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER));
    	Assert.assertTrue(response.contains(CrossOriginFilter.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER));
        Assert.assertFalse(response.contains(CrossOriginFilter.TIMING_ALLOW_ORIGIN_HEADER));
    	Assert.assertTrue(response.contains("Vary"));
    	Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
