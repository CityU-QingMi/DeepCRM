    public void parseContainerPath (final WebAppContext context, final AnnotationParser parser) throws Exception
    {
        //always parse for discoverable annotations as well as class hierarchy and servletcontainerinitializer related annotations
        final Set<Handler> handlers = new HashSet<Handler>();
        handlers.addAll(_discoverableAnnotationHandlers);
        handlers.addAll(_containerInitializerAnnotationHandlers);
        if (_classInheritanceHandler != null)
            handlers.add(_classInheritanceHandler);

        _containerPathStats = new CounterStatistic();

        for (Resource r : context.getMetaData().getContainerResources())
        {
            //queue it up for scanning if using multithreaded mode
            if (_parserTasks != null)
            {
                ParserTask task = new ParserTask(parser, handlers, r);
                _parserTasks.add(task);  
                _containerPathStats.increment();
                if (LOG.isDebugEnabled())
                    task.setStatistic(new TimeStatistic());
            }
        } 
    }
