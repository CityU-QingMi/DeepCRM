    public void testManagedProfileDependency()
        throws Exception
    {
        PomTestWrapper pom = this.buildPom( "managed-profile-dependency/sub", "maven-core-it" );
        assertEquals( 1, ( (List<?>) pom.getValue( "dependencies" ) ).size() );
        assertEquals( "org.apache.maven.its", pom.getValue( "dependencies[1]/groupId" ) );
        assertEquals( "maven-core-it-support", pom.getValue( "dependencies[1]/artifactId" ) );
        assertEquals( "1.3", pom.getValue( "dependencies[1]/version" ) );
        assertEquals( "runtime", pom.getValue( "dependencies[1]/scope" ) );
        assertEquals( 1, ( (List<?>) pom.getValue( "dependencies[1]/exclusions" ) ).size() );
        assertEquals( "commons-lang", pom.getValue( "dependencies[1]/exclusions[1]/groupId" ) );
    }
