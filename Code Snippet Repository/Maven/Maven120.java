    public ArtifactResolutionResult resolveTransitively( Set<Artifact> artifacts, Artifact originatingArtifact,
                                                         Map<String, Artifact> managedVersions,
                                                         ArtifactRepository localRepository,
                                                         List<ArtifactRepository> remoteRepositories,
                                                         ArtifactMetadataSource source, ArtifactFilter filter )
                                                             throws ArtifactResolutionException,
                                                             ArtifactNotFoundException
    {
        return resolveTransitively( artifacts, originatingArtifact, managedVersions, localRepository,
                                    remoteRepositories, source, filter, null );
    }
