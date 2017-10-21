    public Future<ProjectSegment> submit( Callable<ProjectSegment> task )
    {
        FutureTask<ProjectSegment> projectBuildFutureTask = new FutureTask<>( task );
        projectBuildFutureTasks.add( projectBuildFutureTask );
        if ( finishImmediately )
        {
            projectBuildFutureTask.run();
        }
        return projectBuildFutureTask;
    }
