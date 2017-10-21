    public Resource getQuickStartWebXml (WebAppContext context) throws Exception
    {
        Resource webInf = context.getWebInf();
        if (webInf == null || !webInf.exists())
            throw new IllegalStateException("No WEB-INF");
        LOG.debug("webinf={}",webInf);
  
        Resource quickStartWebXml = webInf.addPath("quickstart-web.xml");
        if (!quickStartWebXml.exists())
            throw new IllegalStateException ("No WEB-INF/quickstart-web.xml");
        return quickStartWebXml;
    }
