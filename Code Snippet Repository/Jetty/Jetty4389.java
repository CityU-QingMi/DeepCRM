    @Test
    public void testHeaderFilterSet() throws Exception
    {
        FilterHolder holder = new FilterHolder(HeaderFilter.class);
        holder.setInitParameter("headerConfig","set X-Frame-Options: DENY");
        _tester.getContext().getServletHandler().addFilterWithMapping(holder,"/*",EnumSet.of(DispatcherType.REQUEST));

        HttpTester.Request request = HttpTester.newRequest();
        request.setMethod("GET");
        request.setVersion("HTTP/1.1");
        request.setHeader("Host","localhost");
        request.setURI("/context/test/0");

        HttpTester.Response response = HttpTester.parseResponse(_tester.getResponses(request.generate()));
        Assert.assertTrue(response.contains("X-Frame-Options","DENY"));
    }
