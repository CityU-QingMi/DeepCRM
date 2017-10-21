    @Override
    public MavenExecutionRequest addRemoteRepository( ArtifactRepository repository )
    {
        for ( ArtifactRepository repo : getRemoteRepositories() )
        {
            if ( repo.getId() != null && repo.getId().equals( repository.getId() ) )
            {
                return this;
            }
        }

        getRemoteRepositories().add( repository );

        return this;
    }
