    @Override
    public void preConfigure(final WebAppContext context) throws Exception
    {
        boolean useContainerCache = DEFAULT_USE_CONTAINER_METAINF_CACHE;
        if (context.getServer() != null)
        {
            Boolean attr = (Boolean)context.getServer().getAttribute(USE_CONTAINER_METAINF_CACHE);
            if (attr != null)
                useContainerCache = attr.booleanValue();
        }
        
        if (LOG.isDebugEnabled()) LOG.debug("{} = {}", USE_CONTAINER_METAINF_CACHE, useContainerCache);
        
        //pre-emptively create empty lists for tlds, fragments and resources as context attributes
        //this signals that this class has been called. This differentiates the case where this class
        //has been called but finds no META-INF data from the case where this class was never called
        if (context.getAttribute(METAINF_TLDS) == null)
            context.setAttribute(METAINF_TLDS, new HashSet<URL>());
        if (context.getAttribute(METAINF_RESOURCES) == null)
            context.setAttribute(METAINF_RESOURCES, new HashSet<Resource>());
        if (context.getAttribute(METAINF_FRAGMENTS) == null)
            context.setAttribute(METAINF_FRAGMENTS, new HashMap<Resource, Resource>());

        //always scan everything from the container's classpath
        scanJars(context, context.getMetaData().getContainerResources(), useContainerCache, __allScanTypes);
        //only look for fragments if web.xml is not metadata complete, or it version 3.0 or greater
        List<String> scanTypes = new ArrayList<>(__allScanTypes);
        if (context.getMetaData().isMetaDataComplete() || (context.getServletContext().getEffectiveMajorVersion() < 3) && !context.isConfigurationDiscovered())
            scanTypes.remove(METAINF_FRAGMENTS);
        scanJars(context, context.getMetaData().getWebInfJars(), false, scanTypes);
    }
