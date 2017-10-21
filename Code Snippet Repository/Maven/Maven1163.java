    public void testProfileModules()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "profile-module", "a" );
        assertEquals( "test-prop", pom.getValue( "properties[1]/b" ) );// verifies profile applied
        assertEquals( 4, ( (List<?>) pom.getValue( "modules" ) ).size() );
        assertEquals( "module-2", pom.getValue( "modules[1]" ) );
        assertEquals( "module-1", pom.getValue( "modules[2]" ) );
        assertEquals( "module-3", pom.getValue( "modules[3]" ) );
        assertEquals( "module-4", pom.getValue( "modules[4]" ) );
    }
