    public void testCompileClasspathTransform()
    throws Exception
    {
    	ClasspathContainer res;
    	
    	res = transform.transform( graph, ArtifactScopeEnum.compile, false );

       	assertNotNull("null classpath container after compile transform", res );
       	assertNotNull("null classpath after compile transform", res.getClasspath() );
       	assertEquals("compile classpath should have 3 entries", 3, res.getClasspath().size() );
    }
