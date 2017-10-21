    protected List<ArtifactRepository> getRemoteRepositories()
        throws InvalidRepositoryException
    {
        File repoDir = new File( getBasedir(), "src/test/remote-repo" ).getAbsoluteFile();

        RepositoryPolicy policy = new RepositoryPolicy();
        policy.setEnabled( true );
        policy.setChecksumPolicy( "ignore" );
        policy.setUpdatePolicy( "always" );

        Repository repository = new Repository();
        repository.setId( RepositorySystem.DEFAULT_REMOTE_REPO_ID );
        repository.setUrl( "file://" + repoDir.toURI().getPath() );
        repository.setReleases( policy );
        repository.setSnapshots( policy );

        return Arrays.asList( repositorySystem.buildArtifactRepository( repository ) );
    }
