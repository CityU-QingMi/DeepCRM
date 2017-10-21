    protected List<Resource>  findExtraClasspathDirs(WebAppContext context)
    throws Exception
    { 
        if (context == null || context.getExtraClasspath() == null)
            return null;
        
        List<Resource> dirResources = new ArrayList<Resource>();
        StringTokenizer tokenizer = new StringTokenizer(context.getExtraClasspath(), ",;");
        while (tokenizer.hasMoreTokens())
        {
            Resource resource = context.newResource(tokenizer.nextToken().trim());
            if (resource.exists() && resource.isDirectory())
                dirResources.add(resource);
        }
        
        return dirResources;
    }
