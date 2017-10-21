    public void execute() throws BuildException
    {

        TaskLog.log("Configuring Jetty for project: " + getProject().getName());
        
        setSystemProperties();

        List<Connector> connectorsList = null;

        if (connectors != null)
            connectorsList = connectors.getConnectors();
        else
            connectorsList = new Connectors(jettyPort,30000).getDefaultConnectors();

        List<LoginService> loginServicesList = (loginServices != null?loginServices.getLoginServices():new ArrayList<LoginService>());
        ServerProxyImpl server = new ServerProxyImpl();
        server.setConnectors(connectorsList);
        server.setLoginServices(loginServicesList);
        server.setRequestLog(requestLog);
        server.setJettyXml(jettyXml);
        server.setDaemon(daemon);
        server.setStopPort(stopPort);
        server.setStopKey(stopKey);
        server.setContextHandlers(contextHandlers);
        server.setTempDirectory(tempDirectory);
        server.setScanIntervalSecs(scanIntervalSeconds);

        try
        {
            for (WebAppContext webapp: webapps)
            {
                server.addWebApplication((AntWebAppContext)webapp);
            }
        }
        catch (Exception e)
        {
            throw new BuildException(e);
        }

        server.start();
    }
