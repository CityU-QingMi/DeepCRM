    @SuppressWarnings( "" )
    protected AbstractArtifactResolutionException( String message,
                                                   String groupId,
                                                   String artifactId,
                                                   String version,
                                                   String type,
                                                   String classifier,
                                                   List<ArtifactRepository> remoteRepositories,
                                                   List<String> path,
                                                   Throwable t )
    {
        super( constructMessageBase( message, groupId, artifactId, version, type, remoteRepositories, path ), t );

        this.originalMessage = message;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.type = type;
        this.classifier = classifier;
        this.version = version;
        this.remoteRepositories = remoteRepositories;
        this.path = constructArtifactPath( path, "" );
    }
