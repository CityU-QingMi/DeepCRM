    @Override
    public void configure(WebAppContext context) throws Exception
    {
       context.getObjectFactory().addDecorator(new AnnotationDecorator(context));

       if (!context.getMetaData().isMetaDataComplete())
       {
           //If metadata isn't complete, if this is a servlet 3 webapp or isConfigDiscovered is true, we need to search for annotations
           if (context.getServletContext().getEffectiveMajorVersion() >= 3 || context.isConfigurationDiscovered())
           {
               _discoverableAnnotationHandlers.add(new WebServletAnnotationHandler(context));
               _discoverableAnnotationHandlers.add(new WebFilterAnnotationHandler(context));
               _discoverableAnnotationHandlers.add(new WebListenerAnnotationHandler(context));
           }
       }

       //Regardless of metadata, if there are any ServletContainerInitializers with @HandlesTypes, then we need to scan all the
       //classes so we can call their onStartup() methods correctly
       createServletContainerInitializerAnnotationHandlers(context, getNonExcludedInitializers(context));

       if (!_discoverableAnnotationHandlers.isEmpty() || _classInheritanceHandler != null || !_containerInitializerAnnotationHandlers.isEmpty())
           scanForAnnotations(context);   

       // Resolve container initializers
       List<ContainerInitializer> initializers = 
           (List<ContainerInitializer>)context.getAttribute(AnnotationConfiguration.CONTAINER_INITIALIZERS);
       if (initializers != null && initializers.size()>0)
       {
           Map<String, Set<String>> map = ( Map<String, Set<String>>) context.getAttribute(AnnotationConfiguration.CLASS_INHERITANCE_MAP);
           for (ContainerInitializer i : initializers)
               i.resolveClasses(context,map);
       }
    }
