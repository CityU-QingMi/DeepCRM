    public static void main(String... args) throws Exception
    {   
        if (args.length<1)
            error("No WAR file or directory given");
        
        //war file or dir to start
        String war = args[0];
        
        //optional jetty context xml file to configure the webapp
        Resource contextXml = null;
        if (args.length > 1)
            contextXml = Resource.newResource(args[1]);
        
        Server server = new Server(8080);
        
        QuickStartWebApp webapp = new QuickStartWebApp();
        webapp.setAutoPreconfigure(true);
        webapp.setWar(war);
        webapp.setContextPath("/");

        //apply context xml file
        if (contextXml != null)
        {
            // System.err.println("Applying "+contextXml);
            XmlConfiguration xmlConfiguration = new XmlConfiguration(contextXml.getURL());  
            xmlConfiguration.configure(webapp);   
        }
        
        server.setHandler(webapp);

        server.start();

        
      
        server.join();
    }
