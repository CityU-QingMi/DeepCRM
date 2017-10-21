    @Override
    public MavenExecutionRequest addPluginArtifactRepository( ArtifactRepository repository )
    {
        for ( ArtifactRepository repo : getPluginArtifactRepositories() )
        {
            if ( repo.getId() != null && repo.getId().equals( repository.getId() ) )
            {
                return this;
            }
        }

        getPluginArtifactRepositories().add( repository );

        return this;
    }
