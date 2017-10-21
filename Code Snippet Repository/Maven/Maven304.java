    protected void assertRemoteArtifactNotPresent( Artifact artifact )
        throws Exception
    {
        ArtifactRepository remoteRepo = remoteRepository();

        String path = remoteRepo.pathOf( artifact );

        File file = new File( remoteRepo.getBasedir(), path );

        if ( file.exists() )
        {
            fail( "Remote artifact " + file + " should not be present." );
        }
    }
