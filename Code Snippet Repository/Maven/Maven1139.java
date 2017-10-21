    public void testDuplicatePluginDefinitionsMerged()
        throws Exception
    {
        File f1 = getTestFile( "src/test/resources/projects/duplicate-plugins-merged-pom.xml" );

        MavenProject project = getProject( f1 );
        assertEquals( 2, project.getBuildPlugins().get( 0 ).getDependencies().size() );
        assertEquals( 2, project.getBuildPlugins().get( 0 ).getExecutions().size() );
        assertEquals( "first", project.getBuildPlugins().get( 0 ).getExecutions().get( 0 ).getId() );
    }
