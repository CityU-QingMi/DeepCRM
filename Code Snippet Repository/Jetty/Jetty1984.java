    @Override
    public void preConfigure(WebAppContext context) throws Exception
    {
        //check that webapp is suitable for quick start 
        if (context.getBaseResource() == null)
            throw new IllegalStateException ("No location for webapp");  

        
        //look for quickstart-web.xml in WEB-INF of webapp
        Resource quickStartWebXml = getQuickStartWebXml(context);
        LOG.debug("quickStartWebXml={}",quickStartWebXml);
        
        context.getMetaData().setWebXml(quickStartWebXml);
    }
