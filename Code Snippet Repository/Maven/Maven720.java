    public void execute( MavenSession session, List<MojoExecution> mojoExecutions, ProjectIndex projectIndex )
        throws LifecycleExecutionException

    {
        DependencyContext dependencyContext = newDependencyContext( session, mojoExecutions );

        PhaseRecorder phaseRecorder = new PhaseRecorder( session.getCurrentProject() );

        for ( MojoExecution mojoExecution : mojoExecutions )
        {
            execute( session, mojoExecution, projectIndex, dependencyContext, phaseRecorder );
        }
    }
