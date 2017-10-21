    public void testMissingPom()
        throws Exception
    {
        ArtifactRepository remoteRepository = remoteRepository();

        ArtifactRepository localRepository = localRepository();

        Artifact a = createArtifact( "a", "0.0.1", "pom" );
        File file = new File( localRepository.getBasedir(),
                              localRepository.pathOf( a ) );
        file.delete();
        a.setFile( file );

        File touchFile = updateCheckManager.getTouchfile( a );
        touchFile.delete();

        assertTrue( updateCheckManager.isUpdateRequired( a, remoteRepository ) );

        updateCheckManager.touch( a, remoteRepository, null );

        assertFalse( updateCheckManager.isUpdateRequired( a, remoteRepository ) );

        assertFalse( file.exists() );
        assertNotNull( updateCheckManager.readLastUpdated( touchFile,
                                                           updateCheckManager.getRepositoryKey( remoteRepository ) ) );
    }
