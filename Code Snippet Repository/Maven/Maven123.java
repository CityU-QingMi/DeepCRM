    public ArtifactResolutionResult resolveTransitively( Set<Artifact> artifacts, Artifact originatingArtifact,
                                                         Map<String, Artifact> managedVersions,
                                                         ArtifactRepository localRepository,
                                                         List<ArtifactRepository> remoteRepositories,
                                                         ArtifactMetadataSource source, ArtifactFilter filter,
                                                         List<ResolutionListener> listeners,
                                                         List<ConflictResolver> conflictResolvers )
                                                             throws ArtifactResolutionException,
                                                             ArtifactNotFoundException
    {
        ArtifactResolutionRequest request = new ArtifactResolutionRequest().
            setArtifact( originatingArtifact ).
            setResolveRoot( false ).
            // This is required by the surefire plugin
            setArtifactDependencies( artifacts ).
            setManagedVersionMap( managedVersions ).
            setLocalRepository( localRepository ).
            setRemoteRepositories( remoteRepositories ).
            setCollectionFilter( filter ).
            setListeners( listeners );

        injectSession2( request, legacySupport.getSession() );

        return resolveWithExceptions( request );
    }
