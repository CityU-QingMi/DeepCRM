    public void testArtifactTouchFileName() throws Exception
    {
        ArtifactFactory artifactFactory = (ArtifactFactory) lookup( ArtifactFactory.ROLE );

        ArtifactRepository localRepository = localRepository();

        Artifact a = artifactFactory.createArtifactWithClassifier( "groupdId", "a", "0.0.1-SNAPSHOT", "jar", null );
        File file = new File( localRepository.getBasedir(),
                              localRepository.pathOf( a ) );
        a.setFile( file );

        assertEquals( "a-0.0.1-SNAPSHOT.jar.lastUpdated", updateCheckManager.getTouchfile( a ).getName() );

        a = artifactFactory.createArtifactWithClassifier( "groupdId", "a", "0.0.1-SNAPSHOT", "jar", "classifier" );
        file = new File( localRepository.getBasedir(),
                              localRepository.pathOf( a ) );
        a.setFile( file );

        assertEquals( "a-0.0.1-SNAPSHOT-classifier.jar.lastUpdated", updateCheckManager.getTouchfile( a ).getName() );
    }
