    private void configure()
    {
        if (configured)
            return;
        
        configured = true;

        if(stopPort>0 && stopKey!=null)
        {
            ShutdownMonitor monitor = ShutdownMonitor.getInstance();
            monitor.setPort(stopPort);
            monitor.setKey(stopKey);
            monitor.setExitVm(false);
        }
        
        if (tempDirectory != null && !tempDirectory.exists())
            tempDirectory.mkdirs();
        
        // Applies external configuration via jetty.xml
        applyJettyXml();

        // Configures connectors for this server instance.
        if (connectors != null)
        {
            for (Connector c:connectors)
            {
                ServerConnector jc = new ServerConnector(server);

                jc.setPort(c.getPort());
                jc.setIdleTimeout(c.getMaxIdleTime());

                server.addConnector(jc);
            }
        }

        // Configures login services
        if (loginServices != null)
        {
            for (LoginService ls:loginServices)
            {
                server.addBean(ls);
            }
        }

        // Does not cache resources, to prevent Windows from locking files
        Resource.setDefaultUseCaches(false);

        // Set default server handlers
        configureHandlers();
    }
