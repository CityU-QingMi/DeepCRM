    public ArtifactResolutionResult resolveTransitively( Set<Artifact> artifacts, Artifact originatingArtifact,
                                                         ArtifactRepository localRepository,
                                                         List<ArtifactRepository> remoteRepositories,
                                                         ArtifactMetadataSource source, ArtifactFilter filter )
                                                             throws ArtifactResolutionException,
                                                             ArtifactNotFoundException
    {
        return resolveTransitively( artifacts, originatingArtifact, Collections.<String, Artifact>emptyMap(), localRepository,
                                    remoteRepositories, source, filter );

    }
