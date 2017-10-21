    public void testMng5459()
        throws Exception
    {
        // prepare
        DefaultArtifactDescriptorReader reader = (DefaultArtifactDescriptorReader) lookup( ArtifactDescriptorReader.class );

        RepositoryEventDispatcher eventDispatcher = mock( RepositoryEventDispatcher.class );

        ArgumentCaptor<RepositoryEvent> event = ArgumentCaptor.forClass( RepositoryEvent.class );

        reader.setRepositoryEventDispatcher( eventDispatcher );

        ArtifactDescriptorRequest request = new ArtifactDescriptorRequest();

        request.addRepository( newTestRepository() );

        request.setArtifact( new DefaultArtifact( "org.apache.maven.its", "dep-mng5459", "jar", "0.4.0-SNAPSHOT" ) );

        // execute
        reader.readArtifactDescriptor( session, request );

        // verify
        verify( eventDispatcher ).dispatch( event.capture() );

        boolean missingArtifactDescriptor = false;

        for( RepositoryEvent evt : event.getAllValues() )
        {
            if ( EventType.ARTIFACT_DESCRIPTOR_MISSING.equals( evt.getType() ) )
            {
                assertEquals( "Could not find artifact org.apache.maven.its:dep-mng5459:pom:0.4.0-20130404.090532-2 in repo (" + newTestRepository().getUrl() + ")", evt.getException().getMessage() );
                missingArtifactDescriptor = true;
            }
        }

        if( !missingArtifactDescriptor )
        {
            fail( "Expected missing artifact descriptor for org.apache.maven.its:dep-mng5459:pom:0.4.0-20130404.090532-2" );
        }
    }
