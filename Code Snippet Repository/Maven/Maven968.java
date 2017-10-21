    public ResolutionGroup retrieve( Artifact artifact, ArtifactRepository localRepository,
                                     List<ArtifactRepository> remoteRepositories, boolean resolveManagedVersions )
        throws ArtifactMetadataRetrievalException
    {
        MetadataResolutionRequest request = new DefaultMetadataResolutionRequest();
        injectSession( request );
        request.setArtifact( artifact );
        request.setLocalRepository( localRepository );
        request.setRemoteRepositories( remoteRepositories );
        request.setResolveManagedVersions( resolveManagedVersions );
        return retrieve( request );
    }
