    public InvalidArtifactRTException( String groupId,
                                       String artifactId,
                                       String version,
                                       String type,
                                       String message )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
        this.baseMessage = message;
    }
