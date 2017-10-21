    @Override
    public TldScanner newTldScanner(ServletContext context, boolean namespaceAware, boolean validate, boolean blockExternal)
    {  
        String tmp = context.getInitParameter("org.eclipse.jetty.jsp.precompiled");
        if (tmp!=null && !tmp.equals("") && Boolean.valueOf(tmp))
        {
            if (LOG.isDebugEnabled()) LOG.debug("Jsp precompilation detected");
            return new NullTldScanner(context, namespaceAware, validate, blockExternal);
        }
        
        Collection<URL> tldUrls = (Collection<URL>)context.getAttribute("org.eclipse.jetty.tlds");
        if (tldUrls != null)
        {
            if (LOG.isDebugEnabled()) LOG.debug("Tld pre-scan detected");
            return new TldPreScanned(context,namespaceAware,validate,blockExternal,tldUrls);
        }
        
        if (LOG.isDebugEnabled()) LOG.debug("Defaulting to jasper tld scanning");
        return super.newTldScanner(context, namespaceAware, validate, blockExternal);
    }
