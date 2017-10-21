    public ArtifactMetadata( String groupId, String name, String version, String type, ArtifactScopeEnum artifactScope,
                             String classifier, String artifactUri, String why, boolean resolved, String error )
    {
        this.groupId = groupId;
        this.artifactId = name;
        this.version = version;
        this.type = type;
        this.artifactScope = artifactScope;
        this.classifier = classifier;
        this.artifactUri = artifactUri;
        this.why = why;
        this.resolved = resolved;
        this.error = error;
    }
