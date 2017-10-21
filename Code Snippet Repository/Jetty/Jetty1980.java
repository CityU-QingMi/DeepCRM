    public void doStop () throws Exception
    { 
        if (_classpathFiles != null)
            _classpathFiles.clear();
        _classpathFiles = null;
        
        _classes = null;
        _testClasses = null;
        
        if (_webInfJarMap != null)
            _webInfJarMap.clear();
       
        _webInfClasses.clear();
        _webInfJars.clear();
        
        
        
        // CHECK setShutdown(true);
        //just wait a little while to ensure no requests are still being processed
        Thread.currentThread().sleep(500L);

        super.doStop();

        //remove all listeners, servlets and filters. This is because we will re-apply
        //any context xml file, which means they would potentially be added multiple times.
        setEventListeners(new EventListener[0]);
        getServletHandler().setFilters(new FilterHolder[0]);
        getServletHandler().setFilterMappings(new FilterMapping[0]);
        getServletHandler().setServlets(new ServletHolder[0]);
        getServletHandler().setServletMappings(new ServletMapping[0]);
    }
