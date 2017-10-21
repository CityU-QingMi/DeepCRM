    public void testSystemScopeDependencies()
        throws Exception
    {
        MavenSession session = createMavenSession( null );
        MavenProject project = session.getCurrentProject();

        new ProjectBuilder( project )
            .addDependency( "com.mycompany", "system-dependency", "1.0", Artifact.SCOPE_SYSTEM, new File( getBasedir(), "pom.xml" ).getAbsolutePath() );

        Set<Artifact> artifactDependencies =
            resolver.resolve( project, Collections.singleton( Artifact.SCOPE_COMPILE ), session );
        assertEquals( 1, artifactDependencies.size() );
    }
