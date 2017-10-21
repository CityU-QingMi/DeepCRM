    public void injectAuthentication( RepositorySystemSession session, List<ArtifactRepository> repositories )
    {
        if ( repositories != null && session != null )
        {
            for ( ArtifactRepository repository : repositories )
            {
                repository.setAuthentication( getAuthentication( session, repository ) );
            }
        }
    }
