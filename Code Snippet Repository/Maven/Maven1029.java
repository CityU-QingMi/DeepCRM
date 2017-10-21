    public void testThatErrorDuringProjectDependencyGraphCreationAreStored()
            throws Exception
    {
        Maven maven = getContainer().lookup( Maven.class );
        MavenExecutionRequest request = createMavenExecutionRequest( getProject( "cyclic-reference" ) ).setGoals( asList("validate") );

        MavenExecutionResult result = maven.execute( request );

        assertEquals( ProjectCycleException.class, result.getExceptions().get( 0 ).getClass() );
    }
