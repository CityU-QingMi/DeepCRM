    public ArtifactResolutionResult resolveTransitively( Set<Artifact> artifacts, Artifact originatingArtifact,
                                                         List<ArtifactRepository> remoteRepositories,
                                                         ArtifactRepository localRepository,
                                                         ArtifactMetadataSource source,
                                                         List<ResolutionListener> listeners )
                                                             throws ArtifactResolutionException,
                                                             ArtifactNotFoundException
    {
        return resolveTransitively( artifacts, originatingArtifact, Collections.<String, Artifact>emptyMap(), localRepository,
                                    remoteRepositories, source, null, listeners );
    }
