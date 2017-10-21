    public ArtifactResolutionResult resolveTransitively( Set<Artifact> artifacts, Artifact originatingArtifact,
                                                         Map<String, Artifact> managedVersions,
                                                         ArtifactRepository localRepository,
                                                         List<ArtifactRepository> remoteRepositories,
                                                         ArtifactMetadataSource source )
                                                             throws ArtifactResolutionException,
                                                             ArtifactNotFoundException
    {
        return resolveTransitively( artifacts, originatingArtifact, managedVersions, localRepository,
                                    remoteRepositories, source, null );
    }
