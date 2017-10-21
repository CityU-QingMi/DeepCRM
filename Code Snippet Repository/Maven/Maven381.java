    public void testFalsePluginExecutionInheritValue() throws Exception
    {
        File localRepo = getLocalRepositoryPath();

        File pom0 = new File( localRepo, "p0/pom.xml" );
        File pom0Basedir = pom0.getParentFile();
        File pom1 = new File( pom0Basedir, "p1/pom.xml" );

        getProjectWithDependencies( pom0 );
        MavenProject project1 = getProjectWithDependencies( pom1 );

        Map pluginMap = project1.getBuild().getPluginsAsMap();
        Plugin compilerPlugin = (Plugin) pluginMap.get( "org.apache.maven.plugins:maven-compiler-plugin" );

        assertNotNull( compilerPlugin );

        Map executionMap = compilerPlugin.getExecutionsAsMap();
        assertNull( "Plugin execution: \'test\' should NOT exist in the compiler plugin specification for the child project!", executionMap.get( "test" ) );
    }
