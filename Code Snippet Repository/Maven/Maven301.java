    protected ArtifactRepository remoteRepository()
        throws Exception
    {
        String path = "target/test-repositories/" + component() + "/remote-repository";

        File f = new File( getBasedir(), path );

        ArtifactRepositoryLayout repoLayout =
            (ArtifactRepositoryLayout) lookup( ArtifactRepositoryLayout.ROLE, "default" );

        return artifactRepositoryFactory.createArtifactRepository( "test", "file://" + f.getPath(), repoLayout,
                                                                   new ArtifactRepositoryPolicy(),
                                                                   new ArtifactRepositoryPolicy() );
    }
