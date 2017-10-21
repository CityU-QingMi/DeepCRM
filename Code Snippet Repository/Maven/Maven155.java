    public static ArtifactRepository buildArtifactRepository(
        Repository repo, ArtifactRepositoryFactory artifactRepositoryFactory, PlexusContainer c )
        throws InvalidRepositoryException
    {
        RepositorySystem repositorySystem = rs( c );
        RepositorySystemSession session = rss( c );

        ArtifactRepository repository = repositorySystem.buildArtifactRepository( repo );

        if ( session != null )
        {
            repositorySystem.injectMirror( session, Arrays.asList( repository ) );
            repositorySystem.injectProxy( session, Arrays.asList( repository ) );
            repositorySystem.injectAuthentication( session, Arrays.asList( repository ) );
        }

        return repository;
    }
