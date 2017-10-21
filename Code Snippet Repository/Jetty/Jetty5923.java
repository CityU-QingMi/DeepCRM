    public void scanJars (final WebAppContext context, Collection<Resource> jars, boolean useCaches, List<String> scanTypes )
    throws Exception
    {
        ConcurrentHashMap<Resource, Resource> metaInfResourceCache = null;       
        ConcurrentHashMap<Resource, Resource> metaInfFragmentCache = null;
        ConcurrentHashMap<Resource, Collection<URL>> metaInfTldCache = null;
        if (useCaches)
        {
            metaInfResourceCache = (ConcurrentHashMap<Resource, Resource>)context.getServer().getAttribute(CACHED_CONTAINER_RESOURCES);
            if (metaInfResourceCache == null)
            {
                metaInfResourceCache = new ConcurrentHashMap<Resource,Resource>();
                context.getServer().setAttribute(CACHED_CONTAINER_RESOURCES, metaInfResourceCache);
            }
            metaInfFragmentCache = (ConcurrentHashMap<Resource, Resource>)context.getServer().getAttribute(CACHED_CONTAINER_FRAGMENTS);
            if (metaInfFragmentCache == null)
            {
                metaInfFragmentCache = new ConcurrentHashMap<Resource,Resource>();
                context.getServer().setAttribute(CACHED_CONTAINER_FRAGMENTS, metaInfFragmentCache);
            }
            metaInfTldCache = (ConcurrentHashMap<Resource, Collection<URL>>)context.getServer().getAttribute(CACHED_CONTAINER_TLDS);
            if (metaInfTldCache == null)
            {
                metaInfTldCache = new ConcurrentHashMap<Resource,Collection<URL>>(); 
                context.getServer().setAttribute(CACHED_CONTAINER_TLDS, metaInfTldCache);
            }
        }

        //Scan jars for META-INF information
        if (jars != null)
        {
            for (Resource r : jars)
            {  
                if (scanTypes.contains(METAINF_RESOURCES))
                    scanForResources(context, r, metaInfResourceCache);
                if (scanTypes.contains(METAINF_FRAGMENTS))
                    scanForFragment(context, r, metaInfFragmentCache);
                if (scanTypes.contains(METAINF_TLDS))
                    scanForTlds(context, r, metaInfTldCache);
            }
        }
    }
