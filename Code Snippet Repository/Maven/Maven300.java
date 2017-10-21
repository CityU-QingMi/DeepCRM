    protected ArtifactRepository localRepository()
        throws Exception
    {
        String path = "target/test-repositories/" + component() + "/local-repository";

        File f = new File( getBasedir(), path );

        ArtifactRepositoryLayout repoLayout =
            (ArtifactRepositoryLayout) lookup( ArtifactRepositoryLayout.ROLE, "default" );

        return artifactRepositoryFactory.createArtifactRepository( "local", "file://" + f.getPath(), repoLayout, null,
                                                                   null );
    }
