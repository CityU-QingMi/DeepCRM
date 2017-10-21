    public void parseWebInfClasses (final WebAppContext context, final AnnotationParser parser)
    throws Exception
    {
        Set<Handler> handlers = new HashSet<Handler>();
        handlers.addAll(_discoverableAnnotationHandlers);
        if (_classInheritanceHandler != null)
            handlers.add(_classInheritanceHandler);
        handlers.addAll(_containerInitializerAnnotationHandlers);

        _webInfClassesStats = new CounterStatistic();

        for (Resource dir : context.getMetaData().getWebInfClassesDirs())
        {
            if (_parserTasks != null)
            {
                ParserTask task = new ParserTask(parser, handlers, dir);
                _parserTasks.add(task);
                _webInfClassesStats.increment();
                if (LOG.isDebugEnabled())
                    task.setStatistic(new TimeStatistic());
            }
        }
    }
