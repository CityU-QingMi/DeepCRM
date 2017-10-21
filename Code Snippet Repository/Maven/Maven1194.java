    public void testBuildExtensionInheritance()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "build-extension-inheritance/sub" );
        assertEquals( 3, ( (List<?>) pom.getValue( "build/extensions" ) ).size() );
        assertEquals( "b", pom.getValue( "build/extensions[1]/artifactId" ) );
        assertEquals( "a", pom.getValue( "build/extensions[2]/artifactId" ) );
        assertEquals( "0.2", pom.getValue( "build/extensions[2]/version" ) );
        assertEquals( "c", pom.getValue( "build/extensions[3]/artifactId" ) );
    }
