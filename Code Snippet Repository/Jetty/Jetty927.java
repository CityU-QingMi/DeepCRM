    public static void main( String[] args ) throws Exception
    {
        if (args.length < 4)
            System.err.println("Usage: GCloudSessionTester projectid p12file password serviceaccount");
        
        System.setProperty("org.eclipse.jetty.server.session.LEVEL", "DEBUG");
        
        Server server = new Server(8080);
        HashLoginService loginService = new HashLoginService();
        loginService.setName( "Test Realm" );
        loginService.setConfig( "../../jetty-distribution/target/distribution/demo-base/resources/realm.properties" );
        server.addBean( loginService );


        DefaultSessionIdManager idmgr = new DefaultSessionIdManager(server);
        idmgr.setWorkerName("w1");
        server.setSessionIdManager(idmgr);

 
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("../../jetty-distribution/target/distribution/demo-base/webapps/test.war");
        webapp.addAliasCheck(new AllowSymLinkAliasChecker());
        GCloudSessionDataStore ds = new GCloudSessionDataStore();

        DefaultSessionCache ss = new DefaultSessionCache(webapp.getSessionHandler());
        webapp.getSessionHandler().setSessionCache(ss);
        ss.setSessionDataStore(ds);
        webapp.getSessionHandler().setSessionIdManager(idmgr);

        // A WebAppContext is a ContextHandler as well so it needs to be set to
        // the server so it is aware of where to send the appropriate requests.
        server.setHandler(webapp);

        // Start things up! 
        server.start();

    
        server.join();
    }
