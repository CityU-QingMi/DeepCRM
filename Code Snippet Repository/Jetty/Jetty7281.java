    @Parameters(name="")
    public static List<Object[]> data()
    {
        List<Object[]> setup = new ArrayList<>();
        
        // Servlet3 / AsyncContext Setup
        {
            String description = "Servlet 3 Setup";
            Class<? extends Continuation> expectedImplClass = Servlet3Continuation.class;
            List<String> log = new ArrayList<>();
            RequestLogHandler servlet3Setup = new RequestLogHandler();
            servlet3Setup.setRequestLog(new Log(log));
        
            ServletContextHandler servletContext = new ServletContextHandler();
            servlet3Setup.setHandler(servletContext);
        
            ServletHandler servletHandler=servletContext.getServletHandler();
            List<String> history = new SneakyList();
            Listener listener = new Listener(history);
            ServletHolder holder=new ServletHolder(new SuspendServlet(history, listener));
            holder.setAsyncSupported(true);
            servletHandler.addServletWithMapping(holder, "/");
            setup.add(new Object[]{description,servlet3Setup,history,listener,expectedImplClass,log});
        }
        
        // Faux Continuations Setup
        {
            String description = "Faux Setup";
            Class<? extends Continuation> expectedImplClass = FauxContinuation.class;
            
            // no log for this setup
            List<String> log = null;
            
            ServletContextHandler fauxSetup = new ServletContextHandler();
            ServletHandler servletHandler=fauxSetup.getServletHandler();
            List<String> history = new SneakyList();
            Listener listener = new Listener(history);
            ServletHolder holder=new ServletHolder(new SuspendServlet(history, listener));
            servletHandler.addServletWithMapping(holder,"/");
    
            FilterHolder filter= servletHandler.addFilterWithMapping(ContinuationFilter.class,"/*",null);
            filter.setInitParameter("debug","true");
            filter.setInitParameter("faux","true");
            setup.add(new Object[]{description,fauxSetup,history,listener,expectedImplClass,log});
        }
        
        return setup;
    }
