    public void testReadArtifactDescriptor()
        throws Exception
    {
        Artifact artifact = new DefaultArtifact( "ut.simple:artifact:extension:classifier:1.0" );

        ArtifactDescriptorRequest request = new ArtifactDescriptorRequest();
        request.setArtifact( artifact );
        request.addRepository( newTestRepository() );

        ArtifactDescriptorResult result = system.readArtifactDescriptor( session, request );

        List<Dependency> deps = result.getDependencies();
        assertEquals( 2, deps.size() );
        checkUtSimpleArtifactDependencies( deps.get( 0 ), deps.get( 1 ) );
    }
