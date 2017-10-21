    public Future<ProjectSegment> submit( Runnable task, ProjectSegment result )
    {
        FutureTask<ProjectSegment> projectBuildFutureTask = new FutureTask<>( task, result );
        projectBuildFutureTasks.add( projectBuildFutureTask );
        if ( finishImmediately )
        {
            projectBuildFutureTask.run();
        }
        return projectBuildFutureTask;
    }
