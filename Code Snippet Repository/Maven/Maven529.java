    public ResolutionNode( Artifact artifact, List<ArtifactRepository> remoteRepositories, ResolutionNode parent )
    {
        this.artifact = artifact;
        this.remoteRepositories = remoteRepositories;
        depth = parent.depth + 1;
        parents = new ArrayList<>();
        parents.addAll( parent.parents );
        parents.add( parent.getKey() );
        this.parent = parent;
    }
