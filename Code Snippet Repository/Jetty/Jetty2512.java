    @Override
    public void preConfigure(WebAppContext context) throws Exception
    {
        //check that webapp is suitable for quick start - it is not a packed war
        String war = context.getWar();
        if (war == null || war.length()<=0)
            throw new IllegalStateException ("No location for webapp");  
        
        //Make a temp directory for the webapp if one is not already set
        resolveTempDirectory(context);

        Resource webApp = context.newResource(war);

        // Accept aliases for WAR files
        if (webApp.isAlias())
        {
            LOG.debug(webApp + " anti-aliased to " + webApp.getAlias());
            webApp = context.newResource(webApp.getAlias());
        }

        // Is the WAR usable directly?
        if (!webApp.exists() || !webApp.isDirectory() || webApp.toString().startsWith("jar:"))
            throw new IllegalStateException("Webapp does not exist or is not unpacked");
        
        context.setBaseResource(webApp);

        LOG.debug("webapp={}",webApp);

        
        //look for quickstart-web.xml in WEB-INF of webapp
        Resource quickStartWebXml = getQuickStartWebXml(context);
        LOG.debug("quickStartWebXml={}",quickStartWebXml);
        
        context.getMetaData().setWebXml(quickStartWebXml);
    }
