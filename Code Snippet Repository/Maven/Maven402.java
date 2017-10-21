    public void testUnnecessaryRepositoryLookup()
        throws Exception
    {
        Artifact artifact = createTestPomArtifact( "target/test-data/get-missing-pom" );

        List<ArtifactRepository> repos = new ArrayList<>();
        repos.add( artifactRepositoryFactory.createArtifactRepository( "repo1", "string://url1",
                                                                       new ArtifactRepositoryLayoutStub(), null, null ) );
        repos.add( artifactRepositoryFactory.createArtifactRepository( "repo2", "string://url2",
                                                                       new ArtifactRepositoryLayoutStub(), null, null ) );

        StringWagon wagon = (StringWagon) wagonManager.getWagon( "string" );
        wagon.addExpectedContent( repos.get( 0 ).getLayout().pathOf( artifact ), "expected" );
        wagon.addExpectedContent( repos.get( 1 ).getLayout().pathOf( artifact ), "expected" );

        class TransferListener
            extends AbstractTransferListener
        {
            public List<TransferEvent> events = new ArrayList<>();

            @Override
            public void transferInitiated( TransferEvent transferEvent )
            {
                events.add( transferEvent );
            }
        }

        TransferListener listener = new TransferListener();
        wagonManager.getArtifact( artifact, repos, listener, false );
        assertEquals( 1, listener.events.size() );
    }
