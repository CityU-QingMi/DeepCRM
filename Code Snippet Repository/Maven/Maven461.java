    public void testTestClasspathTransform()
    throws Exception
    {
    	ClasspathContainer res;
    	
    	res = transform.transform( graph, ArtifactScopeEnum.test, false );

       	assertNotNull("null classpath container after runtime transform", res );
       	assertNotNull("null classpath after runtime transform", res.getClasspath() );
       	assertEquals("runtime classpath should have 4 entries", 4, res.getClasspath().size() );
       	
       	ArtifactMetadata md = res.getClasspath().get(3);
       	assertEquals("test artifact version should be 1.2", "1.2", md.getVersion() );
    }
