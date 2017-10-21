    @Override
    public MavenExecutionRequest setRemoteRepositories( List<ArtifactRepository> remoteRepositories )
    {
        if ( remoteRepositories != null )
        {
            this.remoteRepositories = new ArrayList<>( remoteRepositories );
        }
        else
        {
            this.remoteRepositories = null;
        }

        return this;
    }
