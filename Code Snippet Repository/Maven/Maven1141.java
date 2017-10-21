    @Override
    protected ArtifactRepository getLocalRepository()
        throws Exception
    {
        ArtifactRepositoryLayout repoLayout = lookup( ArtifactRepositoryLayout.class, "default" );
        ArtifactRepository r =
            repositorySystem.createArtifactRepository( "local", "file://" + localRepoDir.getAbsolutePath(), repoLayout,
                                                       null, null );
        return r;
    }
