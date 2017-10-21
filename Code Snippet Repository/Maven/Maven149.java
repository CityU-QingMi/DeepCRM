    public MavenProject buildWithDependencies( File pom, ArtifactRepository localRepository,
                                               ProfileManager profileManager, TransferListener transferListener )
        throws ProjectBuildingException, ArtifactResolutionException, ArtifactNotFoundException
    {
        ProjectBuilderConfiguration configuration = new DefaultProjectBuilderConfiguration();
        configuration.setLocalRepository( localRepository );
        configuration.setGlobalProfileManager( profileManager );

        ProjectBuildingRequest request = injectSession( toRequest( configuration ) );

        request.setResolveDependencies( true );

        try
        {
            return projectBuilder.build( pom, request ).getProject();
        }
        catch ( ProjectBuildingException e )
        {
            throw transformError( e );
        }
    }
