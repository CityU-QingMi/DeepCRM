    public ProjectBuildingRequest setRemoteRepositories( List<ArtifactRepository> remoteRepositories )
    {
        if ( remoteRepositories != null )
        {
            this.remoteRepositories = new ArrayList<>( remoteRepositories );
        }
        else
        {
            this.remoteRepositories.clear();
        }

        return this;
    }
