    public void start(BundleContext context) throws Exception
    {    
        Server server = new Server(9999);
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        Dictionary serverProps = new Hashtable();
        //define the unique name of the server instance
        serverProps.put("managedServerName", "fooServer");
        //serverProps.put("jetty.http.port", "9999");
        //register as an OSGi Service for Jetty to find 
        _sr = context.registerService(Server.class.getName(), server, serverProps);
    }
