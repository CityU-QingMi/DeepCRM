    public void doStop()
    {
        try
        {
            scanFiles = null;
            TaskLog.logWithTimestamp("Stopping web application "+this);
            Thread.currentThread().sleep(500L);
            super.doStop();
            //remove all filters, servlets and listeners. They will be recreated
            //either via application of a context xml file or web.xml or annotation or servlet api
            setEventListeners(new EventListener[0]);
            getServletHandler().setFilters(new FilterHolder[0]);
            getServletHandler().setFilterMappings(new FilterMapping[0]);
            getServletHandler().setServlets(new ServletHolder[0]);
            getServletHandler().setServletMappings(new ServletMapping[0]);
        }
        catch (InterruptedException e)
        {
            TaskLog.log(e.toString());
        }
        catch (Exception e)
        {
            TaskLog.log(e.toString());
        }
    }
