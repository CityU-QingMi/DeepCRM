    @Test
    public void getStats()
        throws Exception
    {
        StatisticsHandler statsHandler = new StatisticsHandler();
        _server.setHandler(statsHandler);
        ServletContextHandler statsContext = new ServletContextHandler(statsHandler, "/");
        statsContext.addServlet( new ServletHolder( new TestServlet() ), "/test1" );
        ServletHolder servletHolder = new ServletHolder( new StatisticsServlet() );
        servletHolder.setInitParameter( "restrictToLocalhost", "false" );
        statsContext.addServlet( servletHolder, "/stats" );
        statsContext.setSessionHandler( new SessionHandler() );
        _server.start();

        getResponse("/test1" );
        String response = getResponse("/stats?xml=true" );
        Stats stats = parseStats( response );

        Assert.assertEquals(1, stats.responses2xx);

        getResponse("/stats?statsReset=true" );
        response = getResponse("/stats?xml=true" );
        stats = parseStats( response );

        Assert.assertEquals(1, stats.responses2xx);

        getResponse("/test1" );
        getResponse("/nothing" );
        response = getResponse("/stats?xml=true" );
        stats = parseStats( response );

        Assert.assertEquals(3, stats.responses2xx);
        Assert.assertEquals(1, stats.responses4xx);
    }
