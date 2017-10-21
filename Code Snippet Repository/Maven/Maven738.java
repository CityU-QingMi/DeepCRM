    private Callable<ProjectSegment> createBuildCallable( final MavenSession rootSession,
                                                          final ProjectSegment projectBuild,
                                                          final ReactorContext reactorContext,
                                                          final TaskSegment taskSegment, final ThreadOutputMuxer muxer )
    {
        return new Callable<ProjectSegment>()
        {
            public ProjectSegment call()
            {
                // muxer.associateThreadWithProjectSegment( projectBuild );
                lifecycleModuleBuilder.buildProject( projectBuild.getSession(), rootSession, reactorContext,
                                                     projectBuild.getProject(), taskSegment );
                // muxer.setThisModuleComplete( projectBuild );

                return projectBuild;
            }
        };
    }
