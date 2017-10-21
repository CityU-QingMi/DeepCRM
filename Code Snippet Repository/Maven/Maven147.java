    public MavenProject buildFromRepository( Artifact artifact, List<ArtifactRepository> remoteRepositories,
                                             ProjectBuilderConfiguration configuration, boolean allowStubModel )
        throws ProjectBuildingException
    {
        ProjectBuildingRequest request = injectSession( toRequest( configuration ) );
        request.setRemoteRepositories( normalizeToArtifactRepositories( remoteRepositories, request ) );
        request.setProcessPlugins( false );
        request.setValidationLevel( ModelBuildingRequest.VALIDATION_LEVEL_MINIMAL );

        try
        {
            return projectBuilder.build( artifact, allowStubModel, request ).getProject();
        }
        catch ( ProjectBuildingException e )
        {
            throw transformError( e );
        }
    }
