    public void testInterpolationWithBasedirAlignedDirectories()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "basedir-aligned-interpolation" );
        assertEquals( new File( pom.getBasedir(), "src/main/java" ),
                      new File( pom.getValue( "properties/buildMainSrc" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "src/test/java" ),
                      new File( pom.getValue( "properties/buildTestSrc" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "src/main/scripts" ),
                      new File( pom.getValue( "properties/buildScriptSrc" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "target" ),
                      new File( pom.getValue( "properties/buildOut" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "target/classes" ),
                      new File( pom.getValue( "properties/buildMainOut" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "target/test-classes" ),
                      new File( pom.getValue( "properties/buildTestOut" ).toString() ) );
        assertEquals( new File( pom.getBasedir(), "target/site" ),
                      new File( pom.getValue( "properties/siteOut" ).toString() ) );
    }
