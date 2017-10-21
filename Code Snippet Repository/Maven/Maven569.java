    public void injectMirror( RepositorySystemSession session, List<ArtifactRepository> repositories )
    {
        if ( repositories != null && session != null )
        {
            for ( ArtifactRepository repository : repositories )
            {
                Mirror mirror = getMirror( session, repository );
                injectMirror( repository, mirror );
            }
        }
    }
