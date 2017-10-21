    public ArtifactResolutionResult collect( Set<Artifact> artifacts, Artifact originatingArtifact,
                                             Map<String, Artifact> managedVersions, ArtifactRepository localRepository,
                                             List<ArtifactRepository> remoteRepositories,
                                             ArtifactMetadataSource source, ArtifactFilter filter,
                                             List<ResolutionListener> listeners,
                                             List<ConflictResolver> conflictResolvers )
    {
        ArtifactResolutionRequest request = new ArtifactResolutionRequest();
        request.setLocalRepository( localRepository );
        request.setRemoteRepositories( remoteRepositories );
        injectSession( request );
        return collect( artifacts, originatingArtifact, managedVersions, request, source, filter, listeners,
                        conflictResolvers );
    }
