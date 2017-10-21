    @Test
    public void testIncludeExcludeFilterExcludeOverridesInclude() throws Exception
    {
        FilterHolder holder = new FilterHolder(MockIncludeExcludeFilter.class);
        holder.setInitParameter("includedPaths","^/test/0$");
        holder.setInitParameter("excludedPaths","^/test/0$");
        _tester.getContext().getServletHandler().addFilterWithMapping(holder,"/*",EnumSet.of(DispatcherType.REQUEST));

        HttpTester.Request request = HttpTester.newRequest();
        request.setMethod("GET");
        request.setVersion("HTTP/1.1");
        request.setHeader("Host","localhost");
        request.setURI("/context/test/0");

        HttpTester.Response response = HttpTester.parseResponse(_tester.getResponses(request.generate()));
        Assert.assertFalse(response.contains("X-Custom-Value","1"));
    }
