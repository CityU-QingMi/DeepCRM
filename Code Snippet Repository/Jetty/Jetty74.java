    @Override
    public void deconfigure(WebAppContext context) throws Exception
    {
        context.removeAttribute(CLASS_INHERITANCE_MAP);
        context.removeAttribute(CONTAINER_INITIALIZERS);
        ServletContainerInitializersStarter starter = (ServletContainerInitializersStarter)context.getAttribute(CONTAINER_INITIALIZER_STARTER);
        if (starter != null)
        {
            context.removeBean(starter);
            context.removeAttribute(CONTAINER_INITIALIZER_STARTER);
        }
        
        if (_loadedInitializers != null)
            _loadedInitializers.reload();
    }
