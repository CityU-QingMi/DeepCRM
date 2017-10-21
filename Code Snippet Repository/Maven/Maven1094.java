    public static MavenSession getMavenSession()
    {
        final DefaultMavenExecutionResult defaultMavenExecutionResult = new DefaultMavenExecutionResult();
        MavenExecutionRequest mavenExecutionRequest = new DefaultMavenExecutionRequest();
        mavenExecutionRequest.setExecutionListener( new AbstractExecutionListener() );
        mavenExecutionRequest.setGoals( Arrays.asList( "clean", "aggr", "install" ) );
        mavenExecutionRequest.setDegreeOfConcurrency( 1 );
        final MavenSession session = new MavenSession( null, null, mavenExecutionRequest, defaultMavenExecutionResult );
        final ProjectDependencyGraphStub dependencyGraphStub = new ProjectDependencyGraphStub();
        session.setProjectDependencyGraph( dependencyGraphStub );
        session.setProjects( dependencyGraphStub.getSortedProjects() );
        return session;
    }
