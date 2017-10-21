    public ProjectBuildingRequest setPluginArtifactRepositories( List<ArtifactRepository> pluginArtifactRepositories )
    {
        if ( pluginArtifactRepositories != null )
        {
            this.pluginArtifactRepositories = new ArrayList<>( pluginArtifactRepositories );
        }
        else
        {
            this.pluginArtifactRepositories.clear();
        }

        return this;
    }
