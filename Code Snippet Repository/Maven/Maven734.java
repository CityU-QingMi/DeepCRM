    public void handleBuildError( final ReactorContext buildContext, final MavenSession rootSession,
                                  final MavenSession currentSession, final MavenProject mavenProject, Throwable t,
                                  final long buildStartTime )
    {
        // record the error and mark the project as failed
        long buildEndTime = System.currentTimeMillis();
        buildContext.getResult().addException( t );
        buildContext.getResult().addBuildSummary( new BuildFailure( mavenProject, buildEndTime - buildStartTime, t ) );

        // notify listeners about "soft" project build failures only
        if ( t instanceof Exception && !( t instanceof RuntimeException ) )
        {
            eventCatapult.fire( ExecutionEvent.Type.ProjectFailed, currentSession, null, (Exception) t );
        }

        // reactor failure modes
        if ( t instanceof RuntimeException || !( t instanceof Exception ) )
        {
            // fail fast on RuntimeExceptions, Errors and "other" Throwables
            // assume these are system errors and further build is meaningless
            buildContext.getReactorBuildStatus().halt();
        }
        else if ( MavenExecutionRequest.REACTOR_FAIL_NEVER.equals( rootSession.getReactorFailureBehavior() ) )
        {
            // continue the build
        }
        else if ( MavenExecutionRequest.REACTOR_FAIL_AT_END.equals( rootSession.getReactorFailureBehavior() ) )
        {
            // continue the build but ban all projects that depend on the failed one
            buildContext.getReactorBuildStatus().blackList( mavenProject );
        }
        else if ( MavenExecutionRequest.REACTOR_FAIL_FAST.equals( rootSession.getReactorFailureBehavior() ) )
        {
            buildContext.getReactorBuildStatus().halt();
        }
        else
        {
            logger.error( "invalid reactor failure behavior " + rootSession.getReactorFailureBehavior() );
            buildContext.getReactorBuildStatus().halt();
        }
    }
