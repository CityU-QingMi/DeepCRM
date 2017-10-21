    public void testPluginInheritanceOrder()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "plugin-inheritance-order/child" );

        assertEquals( "maven-it-plugin-log-file", pom.getValue( "build/plugins[1]/artifactId" ) );
        assertEquals( "maven-it-plugin-expression", pom.getValue( "build/plugins[2]/artifactId" ) );
        assertEquals( "maven-it-plugin-configuration", pom.getValue( "build/plugins[3]/artifactId" ) );

        assertEquals( "maven-it-plugin-log-file", pom.getValue( "reporting/plugins[1]/artifactId" ) );
        assertEquals( "maven-it-plugin-expression", pom.getValue( "reporting/plugins[2]/artifactId" ) );
        assertEquals( "maven-it-plugin-configuration", pom.getValue( "reporting/plugins[3]/artifactId" ) );
    }
