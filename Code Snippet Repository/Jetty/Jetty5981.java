    protected List<Resource>  findExtraClasspathJars(WebAppContext context)
    throws Exception
    { 
        if (context == null || context.getExtraClasspath() == null)
            return null;
        
        List<Resource> jarResources = new ArrayList<Resource>();
        StringTokenizer tokenizer = new StringTokenizer(context.getExtraClasspath(), ",;");
        while (tokenizer.hasMoreTokens())
        {
            Resource resource = context.newResource(tokenizer.nextToken().trim());
            String fnlc = resource.getName().toLowerCase(Locale.ENGLISH);
            int dot = fnlc.lastIndexOf('.');
            String extension = (dot < 0 ? null : fnlc.substring(dot));
            if (extension != null && (extension.equals(".jar") || extension.equals(".zip")))
            {
                jarResources.add(resource);
            }
        }
        
        return jarResources;
    }
