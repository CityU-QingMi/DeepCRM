    public List<ArtifactVersion> retrieveAvailableVersions( Artifact artifact, ArtifactRepository localRepository,
                                                            List<ArtifactRepository> remoteRepositories )
        throws ArtifactMetadataRetrievalException
    {
        MetadataResolutionRequest request = new DefaultMetadataResolutionRequest();
        injectSession( request );
        request.setArtifact( artifact );
        request.setLocalRepository( localRepository );
        request.setRemoteRepositories( remoteRepositories );
        return retrieveAvailableVersions( request );
    }
