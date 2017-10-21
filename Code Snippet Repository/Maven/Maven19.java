    public InvalidArtifactRTException( String groupId,
                                       String artifactId,
                                       String version,
                                       String type,
                                       String message,
                                       Throwable cause )
    {
        super( cause );

        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
        this.baseMessage = message;
    }
