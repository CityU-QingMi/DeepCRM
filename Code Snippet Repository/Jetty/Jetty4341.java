    public void startServer(Class<? extends Filter> filter) throws Exception
    {
        _tester = new ServletTester("/ctx");
        
        DefaultSessionCache sessionCache = new DefaultSessionCache(_tester.getContext().getSessionHandler());
        FileSessionDataStore fileStore = new FileSessionDataStore();
       
        Path p = _testDir.getPathFile("sessions");
        FS.ensureEmpty(p);
        fileStore.setStoreDir(p.toFile());
        sessionCache.setSessionDataStore(fileStore);
        
        _tester.getContext().getSessionHandler().setSessionCache(sessionCache);
        
        HttpURI uri = new HttpURI(_tester.createConnector(true));
        _host = uri.getHost();
        _port = uri.getPort();

        _tester.getContext().addServlet(TestServlet.class, "/*");

        FilterHolder dosFilter = _tester.getContext().addFilter(filter, "/dos/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));
        dosFilter.setInitParameter("maxRequestsPerSec", "4");
        dosFilter.setInitParameter("delayMs", "200");
        dosFilter.setInitParameter("throttledRequests", "1");
        dosFilter.setInitParameter("waitMs", "10");
        dosFilter.setInitParameter("throttleMs", "4000");
        dosFilter.setInitParameter("remotePort", "false");
        dosFilter.setInitParameter("insertHeaders", "true");

        FilterHolder timeoutFilter = _tester.getContext().addFilter(filter, "/timeout/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));
        timeoutFilter.setInitParameter("maxRequestsPerSec", "4");
        timeoutFilter.setInitParameter("delayMs", "200");
        timeoutFilter.setInitParameter("throttledRequests", "1");
        timeoutFilter.setInitParameter("waitMs", "10");
        timeoutFilter.setInitParameter("throttleMs", "4000");
        timeoutFilter.setInitParameter("remotePort", "false");
        timeoutFilter.setInitParameter("insertHeaders", "true");
        timeoutFilter.setInitParameter("maxRequestMs", _requestMaxTime + "");

        _tester.start();
    }
