    public void testDependencyInjection()
        throws Exception
    {
        PlexusContainer container = getContainer();

        ComponentDescriptor<? extends AbstractMavenLifecycleParticipant> cd =
            new ComponentDescriptor<>( InjectDependencyLifecycleListener.class,
                                                                        container.getContainerRealm() );
        cd.setRoleClass( AbstractMavenLifecycleParticipant.class );
        container.addComponentDescriptor( cd );

        Maven maven = container.lookup( Maven.class );
        File pom = getProject( "lifecycle-listener-dependency-injection" );
        MavenExecutionRequest request = createMavenExecutionRequest( pom );
        request.setGoals( Arrays.asList( "validate" ) );
        MavenExecutionResult result = maven.execute( request );

        assertFalse( result.getExceptions().toString(), result.hasExceptions() );

        MavenProject project = result.getProject();

        assertEquals( "bar", project.getProperties().getProperty( "foo" ) );

        ArrayList<Artifact> artifacts = new ArrayList<>( project.getArtifacts() );

        assertEquals( 1, artifacts.size() );
        assertEquals( INJECTED_ARTIFACT_ID, artifacts.get( 0 ).getArtifactId() );
    }
