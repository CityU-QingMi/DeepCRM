    public ArtifactRepositoryPolicy getPolicy( ArtifactRepository repository )
    {
        int nature = getNature();
        if ( ( nature & RepositoryMetadata.RELEASE_OR_SNAPSHOT ) == RepositoryMetadata.RELEASE_OR_SNAPSHOT )
        {
            ArtifactRepositoryPolicy policy = new ArtifactRepositoryPolicy( repository.getReleases() );
            policy.merge( repository.getSnapshots() );
            return policy;
        }
        else if ( ( nature & RepositoryMetadata.SNAPSHOT ) != 0 )
        {
            return repository.getSnapshots();
        }
        else
        {
            return repository.getReleases();
        }
    }
