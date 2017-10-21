    public MetadataGraphEdge( String version, boolean resolved, ArtifactScopeEnum scope, String artifactUri, int depth,
                              int pomOrder )
    {
        super();
        this.version = version;
        this.scope = scope;
        this.artifactUri = artifactUri;
        this.depth = depth;
        this.resolved = resolved;
        this.pomOrder = pomOrder;
    }
