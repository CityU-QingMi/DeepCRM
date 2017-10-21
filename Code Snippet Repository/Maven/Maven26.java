    @SuppressWarnings( "" )
    protected AbstractArtifactResolutionException( String message,
                                                   String groupId,
                                                   String artifactId,
                                                   String version,
                                                   String type,
                                                   String classifier,
                                                   List<ArtifactRepository> remoteRepositories,
                                                   List<String> path )
    {
        this( message, groupId, artifactId, version, type, classifier, remoteRepositories, path, null );
    }
