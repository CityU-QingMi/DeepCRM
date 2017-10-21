    protected MavenProject getProjectWithDependencies( File pom )
        throws Exception
    {
        ProjectBuildingRequest configuration = new DefaultProjectBuildingRequest();
        configuration.setLocalRepository( getLocalRepository() );
        configuration.setRemoteRepositories( Arrays.asList( new ArtifactRepository[] {} ) );
        configuration.setProcessPlugins( false );
        configuration.setResolveDependencies( true );
        initRepoSession( configuration );

        try
        {
            return projectBuilder.build( pom, configuration ).getProject();
        }
        catch ( Exception e )
        {
            Throwable cause = e.getCause();
            if ( cause instanceof ModelBuildingException )
            {
                String message = "In: " + pom + "\n\n";
                for ( ModelProblem problem : ( (ModelBuildingException) cause ).getProblems() )
                {
                    message += problem + "\n";
                }
                System.out.println( message );
                fail( message );
            }

            throw e;
        }
    }
