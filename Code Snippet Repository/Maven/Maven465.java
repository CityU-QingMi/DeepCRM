    public void testRuntimeResolution()
    throws Exception
    {
    	MetadataGraph res;
    	
    	res = resolver.resolveConflicts( graph, ArtifactScopeEnum.runtime );
    	
    	assertNotNull("null graph after resolver", res );
    	assertNotNull("no vertices in the resulting graph after resolver", res.getVertices() );
    	assertNotNull("no edges in the resulting graph after resolver", res.getExcidentEdges(v1) );

    	assertEquals( "wrong # of vertices in the resulting graph after resolver", 4, res.getVertices().size() );
    	assertEquals( "wrong # of excident edges in the resulting graph entry after resolver", 2, res.getExcidentEdges(v1).size() );

    	assertEquals( "wrong # of v2 incident edges in the resulting graph after resolver", 1, res.getIncidentEdges(v2).size() );
    	assertEquals( "wrong edge v1-v2 in the resulting graph after resolver", "1.2", res.getIncidentEdges(v2).get(0).getVersion() );

    	assertEquals( "wrong # of edges v1-v3 in the resulting graph after resolver", 1, res.getIncidentEdges(v3).size() );
    	assertEquals( "wrong edge v1-v3 in the resulting graph after resolver", "1.1", res.getIncidentEdges(v3).get(0).getVersion() );

    	assertEquals( "wrong # of edges v3-v4 in the resulting graph after resolver", 1, res.getIncidentEdges(v4).size() );
    	assertEquals( "wrong edge v3-v4 in the resulting graph after resolver", "1.1", res.getIncidentEdges(v4).get(0).getVersion() );
    }
