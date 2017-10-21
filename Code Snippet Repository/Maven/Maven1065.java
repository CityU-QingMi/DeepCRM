    public void testInvalidGoalName()
        throws Exception
    {
        File pom = getProject( "project-basic" );
        MavenSession session = createMavenSession( pom );
        try
        {
            getExecutions( calculateExecutionPlan( session, "resources:" ) );
            fail( "expected a MojoNotFoundException" );
        }
        catch ( MojoNotFoundException e )
        {
            assertEquals( "", e.getGoal() );
        }

        try
        {
            getExecutions( calculateExecutionPlan( session, "org.apache.maven.plugins:maven-resources-plugin:0.1:resources:toomany" ) );
            fail( "expected a MojoNotFoundException" );
        }
        catch ( MojoNotFoundException e )
        {
            assertEquals( "resources:toomany", e.getGoal() );
        }
    }
