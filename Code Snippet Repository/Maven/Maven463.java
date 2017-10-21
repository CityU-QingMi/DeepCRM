    @Override
	protected void setUp() throws Exception
	{
		super.setUp();
		resolver = (GraphConflictResolver) lookup( GraphConflictResolver.ROLE, "default" );

/**/
/**/
/**/
/**/
/**/
/**/
    	graph = new MetadataGraph( 4, 3 );
    	v1 = graph.addVertex(new ArtifactMetadata("g","a1","1.0"));
    	graph.setEntry(v1);
    	v2 = graph.addVertex(new ArtifactMetadata("g","a2","1.0"));
    	v3 = graph.addVertex(new ArtifactMetadata("g","a3","1.0"));
    	v4 = graph.addVertex(new ArtifactMetadata("g","a4","1.0"));

    	// v1-->v2
    	graph.addEdge(v1, v2, new MetadataGraphEdge( "1.1", true, null, null, 2, 1 ) );
    	graph.addEdge(v1, v2, new MetadataGraphEdge( "1.2", true, null, null, 2, 2 ) );
    	
    	// v1-->v3
    	graph.addEdge(v1, v3, new MetadataGraphEdge( "1.1", true, null, null, 2, 1 ) );
    	graph.addEdge(v1, v3, new MetadataGraphEdge( "1.2", true, null, null, 4, 2 ) );
    	
    	// v3-->v4
    	graph.addEdge(v3, v4, new MetadataGraphEdge( "1.1", true, ArtifactScopeEnum.runtime, null, 2, 1 ) );
    	graph.addEdge(v3, v4, new MetadataGraphEdge( "1.2", true, ArtifactScopeEnum.provided, null, 2, 2 ) );
	}
