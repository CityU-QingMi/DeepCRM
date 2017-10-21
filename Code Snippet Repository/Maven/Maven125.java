    public UnresolvedArtifacts( Artifact originatingArtifact,
                                List<Artifact> artifacts,
                                List<ArtifactRepository> remoteRepositories )
    {
        this.originatingArtifact = originatingArtifact;

        this.artifacts = artifacts;

        this.remoteRepositories = remoteRepositories;
    }
