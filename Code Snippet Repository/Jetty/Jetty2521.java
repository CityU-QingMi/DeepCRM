    public void visitContainerInitializer (WebAppContext context, ContainerInitializer containerInitializer)
    {
        if (containerInitializer == null)
            return;
        
        //add the ContainerInitializer to the list of container initializers
        List<ContainerInitializer> containerInitializers = (List<ContainerInitializer>)context.getAttribute(AnnotationConfiguration.CONTAINER_INITIALIZERS);
        if (containerInitializers == null)
        {
            containerInitializers = new ArrayList<ContainerInitializer>();
            context.setAttribute(AnnotationConfiguration.CONTAINER_INITIALIZERS, containerInitializers);
        }
        
        containerInitializers.add(containerInitializer);

        //Ensure a bean is set up on the context that will invoke the ContainerInitializers as the context starts
        ServletContainerInitializersStarter starter = (ServletContainerInitializersStarter)context.getAttribute(AnnotationConfiguration.CONTAINER_INITIALIZER_STARTER);
        if (starter == null)
        {
            starter = new ServletContainerInitializersStarter(context);
            context.setAttribute(AnnotationConfiguration.CONTAINER_INITIALIZER_STARTER, starter);
            context.addBean(starter, true);
        }
    }
