    public void injectProxy( RepositorySystemSession session, List<ArtifactRepository> repositories )
    {
        if ( repositories != null && session != null )
        {
            for ( ArtifactRepository repository : repositories )
            {
                repository.setProxy( getProxy( session, repository ) );
            }
        }
    }
