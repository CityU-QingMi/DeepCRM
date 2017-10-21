    private ExpressionEvaluator createExpressionEvaluator( MavenProject project, PluginDescriptor pluginDescriptor, Properties executionProperties )
        throws Exception
    {
        ArtifactRepository repo = factory.createDefaultLocalRepository();

        MutablePlexusContainer container = (MutablePlexusContainer) getContainer();
        MavenSession session = createSession( container, repo, executionProperties );
        session.setCurrentProject( project );

        MojoDescriptor mojo = new MojoDescriptor();
        mojo.setPluginDescriptor( pluginDescriptor );
        mojo.setGoal( "goal" );

        MojoExecution mojoExecution = new MojoExecution( mojo );

        return new PluginParameterExpressionEvaluator( session, mojoExecution );
    }
