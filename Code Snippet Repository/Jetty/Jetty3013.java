    private ContextHandler(Context context, HandlerContainer parent, String contextPath)
    {
        _scontext = context==null?new Context():context;
        _attributes = new AttributesMap();
        _initParams = new HashMap<String, String>();
        addAliasCheck(new ApproveNonExistentDirectoryAliases());
        if (File.separatorChar=='/')
            addAliasCheck(new AllowSymLinkAliasChecker());

        if (contextPath!=null)
            setContextPath(contextPath);
        if (parent instanceof HandlerWrapper)
            ((HandlerWrapper)parent).setHandler(this);
        else if (parent instanceof HandlerCollection)
            ((HandlerCollection)parent).addHandler(this);
    }
