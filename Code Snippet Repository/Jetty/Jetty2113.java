    protected void parseBundle(WebAppContext context, AnnotationParser parser,
                               Bundle webbundle, Bundle bundle) throws Exception
                               {

        Resource bundleRes = parser.getResource(bundle);  
        Set<Handler> handlers = new HashSet<Handler>();
        handlers.addAll(_discoverableAnnotationHandlers);
        if (_classInheritanceHandler != null)
            handlers.add(_classInheritanceHandler);
        handlers.addAll(_containerInitializerAnnotationHandlers);

        if (_parserTasks != null)
        {
            BundleParserTask task = new BundleParserTask(parser, handlers, bundleRes);
            _parserTasks.add(task);
            if (LOG.isDebugEnabled())
                task.setStatistic(new TimeStatistic());
        }
    }
