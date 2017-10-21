    public ContextHandler createContextHandler(App app) throws Exception
    {
        WebAppContext context = new WebAppContext();

        File war = new File(webappsDir,app.getOriginId().substring(5));
        context.setWar(Resource.newResource(Resource.toURL(war)).toString());

        String path = war.getName();
        
        if (FileID.isWebArchiveFile(war))
        {
            // Context Path is the same as the archive.
            path = path.substring(0,path.length() - 4);
        }
        
        // special case of archive (or dir) named "root" is / context
        if (path.equalsIgnoreCase("root") || path.equalsIgnoreCase("root/"))
            path = URIUtil.SLASH;

        // Ensure "/" is Prepended to all context paths.
        if (path.charAt(0) != '/')
            path = "/" + path;

        // Ensure "/" is Not Trailing in context paths.
        if (path.endsWith("/") && path.length() > 0)
            path = path.substring(0,path.length() - 1);

        context.setContextPath(path);
        
        return context;
    }
