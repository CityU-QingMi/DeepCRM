    public void testMetadata() throws Exception
    {
        ArtifactRepository remoteRepository = remoteRepository();

        ArtifactRepository localRepository = localRepository();

        Artifact a = createRemoteArtifact( "a", "0.0.1-SNAPSHOT" );
        RepositoryMetadata metadata = new ArtifactRepositoryMetadata( a );

        File file = new File( localRepository.getBasedir(),
                              localRepository.pathOfLocalRepositoryMetadata( metadata, localRepository ) );
        file.delete();

        File touchFile = updateCheckManager.getTouchfile( metadata, file );
        touchFile.delete();

        assertTrue( updateCheckManager.isUpdateRequired( metadata, remoteRepository, file ) );

        file.getParentFile().mkdirs();
        file.createNewFile();
        updateCheckManager.touch( metadata, remoteRepository, file );

        assertFalse( updateCheckManager.isUpdateRequired( metadata, remoteRepository, file ) );

        assertNotNull( updateCheckManager.readLastUpdated( touchFile, updateCheckManager.getMetadataKey( remoteRepository, file ) ) );
    }
