    public static List<ArtifactRepository> buildArtifactRepositories(
        List<Repository> repositories, ArtifactRepositoryFactory artifactRepositoryFactory, PlexusContainer c )
        throws InvalidRepositoryException
    {

        List<ArtifactRepository> remoteRepositories = new ArrayList<>();

        for ( Repository r : repositories )
        {
            remoteRepositories.add( buildArtifactRepository( r, artifactRepositoryFactory, c ) );
        }

        return remoteRepositories;
    }
