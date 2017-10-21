    @Test
    public void testRequestWithNoOriginArrivesToApplication() throws Exception
    {
        tester.getContext().addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        final CountDownLatch latch = new CountDownLatch(1);
        tester.getContext().addServlet(new ServletHolder(new ResourceServlet(latch)), "/*");

        String request = "" +
                "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n" +
                "\r\n";
        String response = tester.getResponses(request);
        Assert.assertTrue(response.contains("HTTP/1.1 200"));
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
