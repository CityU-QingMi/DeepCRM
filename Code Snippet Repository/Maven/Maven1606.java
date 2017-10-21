    public void testMissingResourceDirectory()
        throws Exception
    {
        SimpleProblemCollector result = validate( "missing-resource-directory-pom.xml" );

        assertViolations( result, 0, 2, 0 );

        assertEquals( "'build.resources.resource.directory' is missing.", result.getErrors().get( 0 ) );

        assertEquals( "'build.testResources.testResource.directory' is missing.", result.getErrors().get( 1 ) );
    }
