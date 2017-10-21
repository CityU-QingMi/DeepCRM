    private ArtifactRepository injectSession( ArtifactRepository repository, boolean mirrors )
    {
        RepositorySystemSession session = legacySupport.getRepositorySession();

        if ( session != null && repository != null && !isLocalRepository( repository ) )
        {
            List<ArtifactRepository> repositories = Arrays.asList( repository );

            if ( mirrors )
            {
                repositorySystem.injectMirror( session, repositories );
            }

            repositorySystem.injectProxy( session, repositories );

            repositorySystem.injectAuthentication( session, repositories );
        }

        return repository;
    }
