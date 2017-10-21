    public void testInterpolationOfNestedBuildDirectories()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "nested-build-dir-interpolation" );
        assertEquals( new File( pom.getBasedir(), "target/classes/dir0" ),
                      new File( (String) pom.getValue( "properties/dir0" ) ) );
        assertEquals( new File( pom.getBasedir(), "src/test/dir1" ),
                      new File( (String) pom.getValue( "properties/dir1" ) ) );
        assertEquals( new File( pom.getBasedir(), "target/site/dir2" ),
                      new File( (String) pom.getValue( "properties/dir2" ) ) );
    }
