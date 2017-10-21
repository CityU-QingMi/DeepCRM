    public void testWagonTransferListenerRemovedAfterGetArtifactAndPutArtifact()
        throws Exception
    {
        Artifact artifact = createTestArtifact( "target/test-data/transfer-listener", "jar" );
        ArtifactRepository repo = createStringRepo();
        StringWagon wagon = (StringWagon) wagonManager.getWagon( "string" );
        wagon.addExpectedContent( repo.getLayout().pathOf( artifact ), "expected" );

        /* getArtifact */
        assertFalse( "Transfer listener is registered before test",
                     wagon.getTransferEventSupport().hasTransferListener( transferListener ) );
        wagonManager.getArtifact( artifact, repo, transferListener, false );
        assertFalse( "Transfer listener still registered after getArtifact",
                     wagon.getTransferEventSupport().hasTransferListener( transferListener ) );

        /* putArtifact */
        File sampleFile = getTestFile( "target/test-file" );
        FileUtils.fileWrite( sampleFile.getAbsolutePath(), "sample file" );

        assertFalse( "Transfer listener is registered before test", wagon.getTransferEventSupport().hasTransferListener( transferListener ) );
        wagonManager.putArtifact( sampleFile, artifact, repo, transferListener );
        assertFalse( "Transfer listener still registered after putArtifact", wagon.getTransferEventSupport().hasTransferListener( transferListener ) );
    }
