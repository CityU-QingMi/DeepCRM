    private List<RemoteRepository> getRemoteRepositories()
        throws InvalidRepositoryException
    {
        final File repoDir = new File( getBasedir(), "src/test/remote-repo" ).getAbsoluteFile();
        final RemoteRepository remoteRepository =
            new RemoteRepository.Builder( org.apache.maven.repository.RepositorySystem.DEFAULT_REMOTE_REPO_ID,
                                          "default", repoDir.toURI().toASCIIString() ).build();

        return Collections.singletonList( remoteRepository );
    }
