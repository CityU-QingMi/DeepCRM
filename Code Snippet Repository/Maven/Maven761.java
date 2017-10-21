    public void setThisModuleComplete( ProjectSegment projectBuild )
    {
        completedBuilds.add( projectBuild );
        PrintStream stream = printStreams.get( projectBuild );
        synchronized ( stream )
        {
            stream.notifyAll();
        }
        disconnectThreadFromProject();
    }
