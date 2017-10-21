    public void testDefaultPolicy()
        throws Exception
    {
    	MetadataGraphEdge res;
    	
    	res = policy.apply( e1, e2 );
    	assertEquals( "Wrong depth edge selected", "1.1", res.getVersion() );
    	
    	res = policy.apply( e1, e3 );
    	assertEquals( "Wrong version edge selected", "1.2", res.getVersion() );
    }
