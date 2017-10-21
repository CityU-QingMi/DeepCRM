    @Override
    public void postConfigure(WebAppContext context) throws Exception
    {
        Map<String, Set<String>> classMap = (ClassInheritanceMap)context.getAttribute(CLASS_INHERITANCE_MAP);
        List<ContainerInitializer> initializers = (List<ContainerInitializer>)context.getAttribute(CONTAINER_INITIALIZERS);
        
        context.removeAttribute(CLASS_INHERITANCE_MAP);
        if (classMap != null)
            classMap.clear();
        
        context.removeAttribute(CONTAINER_INITIALIZERS);
        if (initializers != null)
            initializers.clear();
        
        if (_discoverableAnnotationHandlers != null)
            _discoverableAnnotationHandlers.clear();

        _classInheritanceHandler = null;
        if (_containerInitializerAnnotationHandlers != null)
            _containerInitializerAnnotationHandlers.clear();

        if (_parserTasks != null)
        {
            _parserTasks.clear();
            _parserTasks = null;
        }
        
        super.postConfigure(context);
    }
