    public TemplateContext(String key, Server server,Resource baseResource, ClassLoader libLoader) throws IOException
    {
        _server=server;
        _baseResource=baseResource;
        _mimeTypes=new MimeTypes();
        _resourceCache=new ResourceCache(null,baseResource,_mimeTypes,false,false);
        
        String[] patterns = (String[])_server.getAttribute(WebAppContext.SERVER_SRV_CLASSES);
        _serverClasses=new ClasspathPattern(patterns==null?WebAppContext.__dftServerClasses:patterns);
        patterns = (String[])_server.getAttribute(WebAppContext.SERVER_SYS_CLASSES);
        _systemClasses=new ClasspathPattern(patterns==null?WebAppContext.__dftSystemClasses:patterns);
        _libLoader=libLoader;
        

        // Is this a webapp or a normal context
        Resource classes=getBaseResource().addPath("WEB-INF/classes/");
        Resource lib=getBaseResource().addPath("WEB-INF/lib/");
        if (classes.exists() && classes.isDirectory() || lib.exists() && lib.isDirectory())
        {
            _webappLoader=new WebAppClassLoader(_libLoader,this);
            _webappLoader.setName(key);
            if (classes.exists())
                _webappLoader.addClassPath(classes);
            if (lib.exists())
                _webappLoader.addJars(lib);            
        }
        else 
            _webappLoader=null;
        
    }
