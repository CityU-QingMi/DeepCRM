    public static void configureConnectors (Server server, Connector connector)
    {
        if (server == null)
            throw new IllegalArgumentException("Server is null");
        
        //if a connector is provided, use it
        if (connector != null)
        {
            server.addConnector(connector);
            return;
        }
        
        

        // if the user hasn't configured the connectors in a jetty.xml file so use a default one
        Connector[] connectors = server.getConnectors();
        if (connectors == null || connectors.length == 0)
        {
            //Make a new default connector
            MavenServerConnector tmp = new MavenServerConnector();               
            //use any jetty.http.port settings provided
            String port = System.getProperty(MavenServerConnector.PORT_SYSPROPERTY, System.getProperty("jetty.port", MavenServerConnector.DEFAULT_PORT_STR));
            tmp.setPort(Integer.parseInt(port.trim()));
            tmp.setServer(server);
            server.setConnectors(new Connector[] {tmp});
        }
    }
