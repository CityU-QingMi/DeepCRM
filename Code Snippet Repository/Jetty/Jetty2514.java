    @Override
    public void configure(WebAppContext context) throws Exception
    {
        LOG.debug("configure {}",this);
        if (context.isStarted())
        {
            LOG.warn("Cannot configure webapp after it is started");
            return;
        }
        
        //Temporary:  set up the classpath here. This should be handled by the QuickStartDescriptorProcessor
        Resource webInf = context.getWebInf();

        if (webInf != null && webInf.isDirectory() && context.getClassLoader() instanceof WebAppClassLoader)
        {
            // Look for classes directory
            Resource classes= webInf.addPath("classes/");
            if (classes.exists())
                ((WebAppClassLoader)context.getClassLoader()).addClassPath(classes);

            // Look for jars
            Resource lib= webInf.addPath("lib/");
            if (lib.exists() || lib.isDirectory())
                ((WebAppClassLoader)context.getClassLoader()).addJars(lib);
        }

        //add the processor to handle normal web.xml content
        context.getMetaData().addDescriptorProcessor(new StandardDescriptorProcessor());
        
        //add a processor to handle extended web.xml format
        context.getMetaData().addDescriptorProcessor(new QuickStartDescriptorProcessor());
        
        //add a decorator that will find introspectable annotations
        context.getObjectFactory().addDecorator(new AnnotationDecorator(context)); //this must be the last Decorator because they are run in reverse order!
        
        //add a context bean that will run ServletContainerInitializers as the context starts
        ServletContainerInitializersStarter starter = (ServletContainerInitializersStarter)context.getAttribute(AnnotationConfiguration.CONTAINER_INITIALIZER_STARTER);
        if (starter != null)
            throw new IllegalStateException("ServletContainerInitializersStarter already exists");
        starter = new ServletContainerInitializersStarter(context);
        context.setAttribute(AnnotationConfiguration.CONTAINER_INITIALIZER_STARTER, starter);
        context.addBean(starter, true);       

        LOG.debug("configured {}",this);
    }
