    public void testBuildParentVersionRangeLocallyWithoutChildVersion() throws Exception
    {
        File f1 =
            getTestFile( "src/test/resources/projects/parent-version-range-local-child-without-version/child/pom.xml" );

        try
        {
            getProject( f1 );
            fail( "Expected 'ProjectBuildingException' not thrown." );
        }
        catch ( final ProjectBuildingException e )
        {
            assertNotNull( e.getMessage() );
            assertTrue( e.getMessage().contains( "Version must be a constant" ) );
        }
    }
