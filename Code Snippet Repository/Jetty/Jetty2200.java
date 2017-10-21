    public void start(BundleContext context) throws Exception
    {
        //Create webappA as a Service and target it at the default server
        WebAppContext webapp = new WebAppContext();
        webapp.addServlet(new ServletHolder(new TestServlet()), "/mime");
        Dictionary props = new Hashtable();
        props.put("war","webappA");
        props.put("contextPath","/acme");
        props.put("managedServerName", "defaultJettyServer");
        _srA = context.registerService(WebAppContext.class.getName(),webapp,props);
        
        //Create a second webappB as a Service and target it at a custom Server
        //deployed by another bundle
        WebAppContext webappB = new WebAppContext();
        Dictionary propsB = new Hashtable();
        propsB.put("war", "webappB");
        propsB.put("contextPath", "/acme");
        propsB.put("managedServerName", "fooServer");
        _srB = context.registerService(WebAppContext.class.getName(), webappB, propsB);
    }
