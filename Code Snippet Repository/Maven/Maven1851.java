    public void testCollectDependencies()
        throws Exception
    {
        Artifact artifact = new DefaultArtifact( "ut.simple:artifact:extension:classifier:1.0" );
        // notice: extension and classifier not really used in this test...

        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setRoot( new Dependency( artifact, null ) );
        collectRequest.addRepository( newTestRepository() );

        CollectResult collectResult = system.collectDependencies( session, collectRequest );

        List<DependencyNode> nodes = collectResult.getRoot().getChildren();
        assertEquals( 2, nodes.size() );
        checkUtSimpleArtifactDependencies( nodes.get( 0 ).getDependency(), nodes.get( 1 ).getDependency() );
    }
