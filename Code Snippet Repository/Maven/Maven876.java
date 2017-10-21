    @Override
    public ProjectBuildingResult build( Artifact artifact, boolean allowStubModel, ProjectBuildingRequest request )
        throws ProjectBuildingException
    {
        org.eclipse.aether.artifact.Artifact pomArtifact = RepositoryUtils.toArtifact( artifact );
        pomArtifact = ArtifactDescriptorUtils.toPomArtifact( pomArtifact );

        InternalConfig config = new InternalConfig( request, null, null );

        boolean localProject;

        try
        {
            ArtifactRequest pomRequest = new ArtifactRequest();
            pomRequest.setArtifact( pomArtifact );
            pomRequest.setRepositories( config.repositories );
            ArtifactResult pomResult = repoSystem.resolveArtifact( config.session, pomRequest );

            pomArtifact = pomResult.getArtifact();
            localProject = pomResult.getRepository() instanceof WorkspaceRepository;
        }
        catch ( org.eclipse.aether.resolution.ArtifactResolutionException e )
        {
            if ( e.getResults().get( 0 ).isMissing() && allowStubModel )
            {
                return build( null, createStubModelSource( artifact ), config );
            }
            throw new ProjectBuildingException( artifact.getId(),
                                                "Error resolving project artifact: " + e.getMessage(), e );
        }

        File pomFile = pomArtifact.getFile();

        if ( "pom".equals( artifact.getType() ) )
        {
            artifact.selectVersion( pomArtifact.getVersion() );
            artifact.setFile( pomFile );
            artifact.setResolved( true );
        }

        return build( localProject ? pomFile : null, new FileModelSource( pomFile ), config );
    }
