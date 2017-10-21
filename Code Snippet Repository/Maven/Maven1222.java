    private ModelResolver newModelResolver() throws Exception
    {
        final File localRepo = new File( this.getLocalRepository().getBasedir() );
        final DefaultRepositorySystemSession repoSession = MavenRepositorySystemUtils.newSession();
        repoSession.setLocalRepositoryManager( new LegacyLocalRepositoryManager( localRepo ) );

        return new ProjectModelResolver( repoSession, null, lookup( RepositorySystem.class ),
                                         lookup( RemoteRepositoryManager.class ),
                                         this.getRemoteRepositories(),
                                         ProjectBuildingRequest.RepositoryMerging.REQUEST_DOMINANT, null );

    }
