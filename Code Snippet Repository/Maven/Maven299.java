    protected ArtifactRepository badLocalRepository()
        throws Exception
    {
        String path = "target/test-repositories/" + component() + "/bad-local-repository";

        File f = new File( getBasedir(), path );

        f.createNewFile();

        ArtifactRepositoryLayout repoLayout =
            (ArtifactRepositoryLayout) lookup( ArtifactRepositoryLayout.ROLE, "default" );

        return artifactRepositoryFactory.createArtifactRepository( "test", "file://" + f.getPath(), repoLayout, null,
                                                                   null );
    }
