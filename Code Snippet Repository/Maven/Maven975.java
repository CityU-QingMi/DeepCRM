    public List<ArtifactVersion> retrieveAvailableVersions( MetadataResolutionRequest request )
        throws ArtifactMetadataRetrievalException
    {
        RepositoryMetadata metadata = new ArtifactRepositoryMetadata( request.getArtifact() );

        try
        {
            repositoryMetadataManager.resolve( metadata, request );
        }
        catch ( RepositoryMetadataResolutionException e )
        {
            throw new ArtifactMetadataRetrievalException( e.getMessage(), e, request.getArtifact() );
        }

        List<String> availableVersions = request.getLocalRepository().findVersions( request.getArtifact() );

        return retrieveAvailableVersionsFromMetadata( metadata.getMetadata(), availableVersions );
    }
