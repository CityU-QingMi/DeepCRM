    public void testInheritedPropertiesInterpolatedWithValuesFromChildWithActiveProfiles()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "inherited-properties-interpolation/active-profile/sub" );

        assertEquals( 1, pom.getMavenProject().getModel().getProfiles().size() );

        buildPom( "inherited-properties-interpolation/active-profile/sub", "it-parent", "it-child" );
        assertEquals( "CHILD", pom.getValue( "properties/overridden" ) );
        assertEquals( "CHILD", pom.getValue( "properties/interpolated" ) );
    }
