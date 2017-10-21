    public void testGetMissingJar() throws TransferFailedException, UnsupportedProtocolException, IOException
    {
        Artifact artifact = createTestArtifact( "target/test-data/get-missing-jar", "jar" );

        ArtifactRepository repo = createStringRepo();

        try
        {
            wagonManager.getArtifact( artifact, repo, null, false );

            fail();
        }
        catch ( ResourceDoesNotExistException e )
        {
            assertTrue( true );
        }

        assertFalse( artifact.getFile().exists() );
    }
