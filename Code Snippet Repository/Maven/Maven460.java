    public void testRuntimeClasspathTransform()
    throws Exception
    {
    	ClasspathContainer res;
    	
    	res = transform.transform( graph, ArtifactScopeEnum.runtime, false );

       	assertNotNull("null classpath container after runtime transform", res );
       	assertNotNull("null classpath after runtime transform", res.getClasspath() );
       	assertEquals("runtime classpath should have 4 entries", 4, res.getClasspath().size() );
       	
       	ArtifactMetadata md = res.getClasspath().get(3);
       	assertEquals("runtime artifact version should be 1.1", "1.1", md.getVersion() );
    }
