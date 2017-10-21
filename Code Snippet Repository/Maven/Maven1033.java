    private <T> List<String> getReactorOrder( String testProject, Class<T> participant )
        throws Exception
    {
        PlexusContainer container = getContainer();

        ComponentDescriptor<T> cd = new ComponentDescriptor<>( participant, container.getContainerRealm() );
        cd.setRoleClass( AbstractMavenLifecycleParticipant.class );
        container.addComponentDescriptor( cd );

        Maven maven = container.lookup( Maven.class );
        File pom = getProject( testProject );
        MavenExecutionRequest request = createMavenExecutionRequest( pom );
        request.setGoals( Arrays.asList( "validate" ) );
        MavenExecutionResult result = maven.execute( request );

        assertFalse( result.getExceptions().toString(), result.hasExceptions() );

        List<String> order = new ArrayList<>();
        for ( MavenProject project : result.getTopologicallySortedProjects() )
        {
            order.add( project.getArtifactId() );
        }
        return order;
    }
