    private PrintStream getThreadBoundPrintStream()
    {
        ProjectSegment threadProject = projectBuildThreadLocal.get();
        if ( threadProject == null )
        {
            return defaultPrintStream;
        }
        if ( ownsRealOutputStream( threadProject ) )
        {
            return originalSystemOUtStream;
        }
        return printStreams.get( threadProject );
    }
