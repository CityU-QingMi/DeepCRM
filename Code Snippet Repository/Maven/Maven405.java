    public void testGetRemoteJar()
        throws TransferFailedException, ResourceDoesNotExistException, UnsupportedProtocolException, IOException,
        AuthorizationException
    {
        Artifact artifact = createTestArtifact( "target/test-data/get-remote-jar", "jar" );

        ArtifactRepository repo = createStringRepo();

        StringWagon wagon = (StringWagon) wagonManager.getWagon( "string" );
        wagon.addExpectedContent( repo.getLayout().pathOf( artifact ), "expected" );

        wagonManager.getArtifact( artifact, repo, null, false );

        assertTrue( artifact.getFile().exists() );
        assertEquals( "expected", FileUtils.fileRead( artifact.getFile(), "UTF-8" ) );
    }
