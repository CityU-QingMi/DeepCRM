    public static Server applyXmlConfigurations (Server server, List<File> files) 
    throws Exception
    {
        if (files == null || files.isEmpty())
            return server;

        Map<String,Object> lastMap = new HashMap<String,Object>();
        
        if (server != null)
            lastMap.put("Server", server);
     

        for ( File xmlFile : files )
        {
            if (PluginLog.getLog() != null)
                PluginLog.getLog().info( "Configuring Jetty from xml configuration file = " + xmlFile.getCanonicalPath() );   


            XmlConfiguration xmlConfiguration = new XmlConfiguration(Resource.toURL(xmlFile));

            //chain ids from one config file to another
            if (lastMap != null)
                xmlConfiguration.getIdMap().putAll(lastMap); 

            //Set the system properties each time in case the config file set a new one
            Enumeration<?> ensysprop = System.getProperties().propertyNames();
            while (ensysprop.hasMoreElements())
            {
                String name = (String)ensysprop.nextElement();
                xmlConfiguration.getProperties().put(name,System.getProperty(name));
            }
            xmlConfiguration.configure(); 
            lastMap = xmlConfiguration.getIdMap();
        }
        
        return (Server)lastMap.get("Server");
    }
