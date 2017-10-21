    @Override
    public MavenExecutionRequest populateDefaults( MavenExecutionRequest request )
        throws MavenExecutionRequestPopulationException
    {
        baseDirectory( request );

        localRepository( request );

        populateDefaultPluginGroups( request );

        injectDefaultRepositories( request );

        injectDefaultPluginRepositories( request );

        return request;
    }
