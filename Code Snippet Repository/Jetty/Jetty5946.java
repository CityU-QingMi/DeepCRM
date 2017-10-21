    @Override
    public Enumeration<URL> getResources(String name) throws IOException
    {
        List<URL> from_parent = new ArrayList<>();
        List<URL> from_webapp = new ArrayList<>();
        
        Enumeration<URL> urls = _parent.getResources(name);
        while (urls!=null && urls.hasMoreElements())
        {
            URL url = urls.nextElement();
            if (Boolean.TRUE.equals(__loadServerClasses.get()) || !_context.isServerResource(name,url))
                from_parent.add(url);
        }

        urls = this.findResources(name);
        while (urls!=null && urls.hasMoreElements())
        {
            URL url = urls.nextElement();
            if (!_context.isSystemResource(name,url) || from_parent.isEmpty())
                from_webapp.add(url);
        }

        List<URL> resources;
        
        if (_context.isParentLoaderPriority())
        {
            from_parent.addAll(from_webapp);
            resources = from_parent;
        }
        else
        {
            from_webapp.addAll(from_parent);
            resources = from_webapp;
        }
        
        if (LOG.isDebugEnabled())
            LOG.debug("getResources {} {}",name,resources);

        return Collections.enumeration(resources);
    }
