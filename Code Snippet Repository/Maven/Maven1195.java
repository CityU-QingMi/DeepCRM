    public void testJdkActivation()
    	throws Exception
	{
    	Properties props = new Properties();
        props.put( "java.version", "1.5.0_15" );

        PomTestWrapper pom = buildPom( "jdk-activation", props );
        assertEquals( 3, pom.getMavenProject().getActiveProfiles().size() );
        assertEquals( "PASSED", pom.getValue( "properties/jdkProperty3" ) );
        assertEquals( "PASSED", pom.getValue( "properties/jdkProperty2" ) );
        assertEquals( "PASSED", pom.getValue( "properties/jdkProperty1" ) );
	}
