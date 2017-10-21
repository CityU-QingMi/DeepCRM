    public void testPom() throws Exception
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

        file.getParentFile().mkdirs();
        file.createNewFile();
        updateCheckManager.touch( a, remoteRepository, null );

        assertFalse( updateCheckManager.isUpdateRequired( a, remoteRepository ) );

        assertNull( updateCheckManager.readLastUpdated( touchFile,
                                                        updateCheckManager.getRepositoryKey( remoteRepository ) ) );

        assertFalse( updateCheckManager.getTouchfile( a ).exists() );
    }
