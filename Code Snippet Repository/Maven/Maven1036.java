    public void testSystemScopeDependencyIsPresentInTheCompileClasspathElements()
        throws Exception
    {
        File pom = getProject( "it0063" );

        Properties eps = new Properties();
        eps.setProperty( "jre.home", new File( pom.getParentFile(), "jdk/jre" ).getPath() );

        MavenSession session = createMavenSession( pom, eps );
        MavenProject project = session.getCurrentProject();

        project.setArtifacts( resolver.resolve( project, Collections.singleton( Artifact.SCOPE_COMPILE ), session ) );

        List<String> elements = project.getCompileClasspathElements();
        assertEquals( 2, elements.size() );

        @SuppressWarnings( "deprecation" )
        List<Artifact> artifacts = project.getCompileArtifacts();
        assertEquals( 1, artifacts.size() );
        assertTrue( artifacts.get( 0 ).getFile().getName().endsWith( "tools.jar" ) );
    }
